package ar.edu.unicen.gatewayservice.filters;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class RoleFilter extends AbstractGatewayFilterFactory<RoleFilter.Config>{
    public RoleFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            // Extrae el rol del token ya validado por JwtAuthFilter
            String role = exchange.getRequest()
                    .getHeaders()
                    .getFirst("X-User-Role");

            if (role == null) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            // Convierte la lista "ADMIN,USER" a List<String>
            List<String> allowedRoles =
                    Arrays.asList(config.allowedRoles.replace(" ", "").split(","));

            // Validación final
            if (!allowedRoles.contains(role)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        // ejemplo: "ADMIN", "USER,ADMIN", "SUPPORT,ADMIN"
        private String allowedRoles;
    }
}
