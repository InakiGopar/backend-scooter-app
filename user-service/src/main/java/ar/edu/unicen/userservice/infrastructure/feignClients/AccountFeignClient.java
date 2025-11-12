package ar.edu.unicen.userservice.infrastructure.feignClients;

import ar.edu.unicen.userservice.domain.dtos.response.CancelAccountDTO;
import ar.edu.unicen.userservice.domain.dtos.response.UserScooterUsageResponseDTO;
import ar.edu.unicen.userservice.domain.model.account.AccountType;
import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="account-service", url="http://localhost:8084/account")
public interface AccountFeignClient {
    @PatchMapping("/toggleState/{userId}")
    CancelAccountDTO toggleAccountState(@PathVariable Long userId);

    //Report E
    @GetMapping("/scooter-user-usage")
    List<UserScooterUsageResponseDTO>getScooterUserUsage(@RequestParam int monthStart, @RequestParam int monthEnd, @RequestParam AccountType userType);

}
