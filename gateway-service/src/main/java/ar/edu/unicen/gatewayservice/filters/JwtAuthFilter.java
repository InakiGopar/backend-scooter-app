package ar.edu.unicen.gatewayservice.filters;


import ar.edu.unicen.gatewayservice.domain.dtos.ValidateTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GlobalFilter, Ordered {
    private final WebClient.Builder webClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Public routes (login/register)
        if (path.startsWith("/auth/login") || path.startsWith("/auth/register")) {
            return chain.filter(exchange);
        }

        // Get token
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange, "Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        // Validated token use AUTH-SERVICE
        return webClient.build()
                .get()
                .uri("http://localhost:8091/auth/validate")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(ValidateTokenResponse.class)
                .flatMap(response -> {

                    if (!response.valid()) {
                        return unauthorized(exchange, "Invalid token");
                    }

                    // Mutar request enviando info del usuario
                    ServerHttpRequest mutatedRequest = exchange.getRequest()
                            .mutate()
                            .header("X-User-Name", response.username())
                            .header("X-User-Role", response.role().name())
                            .build();

                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                })
                .onErrorResume(e -> unauthorized(exchange, "Error validating token"));
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] bytes = message.getBytes();
        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(bytes))
        );
    }

    @Override
    public int getOrder() {
        return -1; // Ejecuta antes del enrutamiento
    }

}
