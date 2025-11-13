package ar.edu.unicen.walletservice.infrastructure.feingClients;

import ar.edu.unicen.walletservice.domain.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="account-service", url="http://localhost:8083/api/account")
public interface AccountFeignClient {
    @GetMapping("/{id}")
    Account findAccountById(@PathVariable Long id);
}
