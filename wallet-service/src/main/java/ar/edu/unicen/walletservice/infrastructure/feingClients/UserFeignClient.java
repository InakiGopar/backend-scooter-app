package ar.edu.unicen.walletservice.infrastructure.feingClients;

import ar.edu.unicen.walletservice.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="user-service", url="http://localhost:8082/user")
public interface UserFeignClient {
    @GetMapping("/{id}")
    User findUserById(@PathVariable Long id);
}
