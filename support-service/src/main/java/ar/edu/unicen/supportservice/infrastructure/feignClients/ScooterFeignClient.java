package ar.edu.unicen.supportservice.infrastructure.feignClients;

import ar.edu.unicen.supportservice.domain.model.Scooter;
import ar.edu.unicen.supportservice.domain.model.ScooterState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="scooter-service", url="http://localhost:8081/scooter")
public interface ScooterFeignClient {
    @GetMapping("/{id}")
    Scooter getScooterById(@PathVariable("id") Long id);

    @PatchMapping("/{id}/status")
    void updateScooterStatus(@PathVariable("id") Long id, @RequestParam ScooterState status);

}
