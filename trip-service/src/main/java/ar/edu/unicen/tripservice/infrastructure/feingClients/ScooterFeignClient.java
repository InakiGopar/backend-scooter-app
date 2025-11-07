package ar.edu.unicen.tripservice.infrastructure.feingClients;

import ar.edu.unicen.tripservice.domain.model.scooter.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "scooter-service", url = "http://localhost:8081/scooter")
public interface ScooterFeignClient {

    @GetMapping("/{scooterId}")
    Scooter getScooterById(@PathVariable Long scooterId);

    @PatchMapping("/{scooterId}/stop")
    void updateScooterStop(@PathVariable Long scooterId, @RequestBody Scooter scooter);

    @PatchMapping("/{scooterId}/status")
    void updateScooterStatus(@PathVariable Long scooterId, @RequestBody Scooter scooter);
}
