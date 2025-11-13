
package ar.edu.unicen.tripservice.infrastructure.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account-service", url = "http://localhost:8084/api/AccountUser")
public interface AccountFeignClient {
    @GetMapping("/{accountId}")
    List<AccountUserResponseDTO> getAccountUsers(@PathVariable("accountId") Long accountId);

    // DTO local
    record AccountUserResponseDTO(Long accountId, Long userId) {}
}

