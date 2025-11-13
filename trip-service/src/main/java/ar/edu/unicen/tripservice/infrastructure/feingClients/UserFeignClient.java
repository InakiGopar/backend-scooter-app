package ar.edu.unicen.tripservice.infrastructure.feingClients;

import ar.edu.unicen.tripservice.domain.model.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8082/api/user")
public interface UserFeignClient {
    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") Long id);
}
