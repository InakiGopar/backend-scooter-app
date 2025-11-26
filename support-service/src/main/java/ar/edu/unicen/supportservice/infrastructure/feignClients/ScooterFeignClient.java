package ar.edu.unicen.supportservice.infrastructure.feignClients;

import ar.edu.unicen.supportservice.domain.model.Scooter;
import ar.edu.unicen.supportservice.domain.model.ScooterState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="scooter-service", url="http://localhost:8081/api/scooter")
public interface ScooterFeignClient {
    @GetMapping("/{scooterId}")
    Scooter getScooterById(@PathVariable Long scooterId);

    @PatchMapping("/{scooterId}/state")
    void updateScooterState(@PathVariable Long scooterId, @RequestParam ScooterState state);

}
