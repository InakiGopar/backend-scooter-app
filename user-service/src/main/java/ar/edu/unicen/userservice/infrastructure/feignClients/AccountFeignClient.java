package ar.edu.unicen.userservice.infrastructure.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="account-service", url="http://localhost:8084/account")
public interface AccountFeignClient {
    @PatchMapping("/toggleState/{userId}")
    void toggleAccountState(@PathVariable Long userId);
}
