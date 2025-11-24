package ar.edu.unicen.authservice.infrastructure.feignClients;

import ar.edu.unicen.authservice.domain.dtos.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="user-service", url ="http://localhost:8082/api/user")
public interface UserFeignClient {
    @GetMapping("/email/{userEmail}")
    UserResponseDTO findByEmail(@PathVariable String userEmail);
}
