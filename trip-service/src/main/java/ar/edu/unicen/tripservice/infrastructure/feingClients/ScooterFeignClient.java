package ar.edu.unicen.tripservice.infrastructure.feingClients;

import ar.edu.unicen.tripservice.domain.dtos.request.scooter.FeignScooterRequest;
import ar.edu.unicen.tripservice.domain.model.scooter.Scooter;
import ar.edu.unicen.tripservice.domain.model.scooter.Stop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "scooter-service", url = "http://localhost:8081/api")
public interface ScooterFeignClient {

    @GetMapping("/scooter/{scooterId}")
    Scooter getScooterById(@PathVariable Long scooterId);

    @PatchMapping("/scooter/{scooterId}/stop")
    void updateScooter(@PathVariable Long scooterId, @RequestBody Scooter scooter);

    @PatchMapping("/scooter/{scooterId}/status")
    void updateScooterStatus(@PathVariable Long scooterId, @RequestBody FeignScooterRequest scooter);

    @GetMapping("/stop/{stopId}")
    Stop getStopById(@PathVariable Long stopId);

}
