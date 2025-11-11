package ar.edu.unicen.userservice.infrastructure.feignClients;

import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name="scooter-service", url="http://localhost:8081/scooter")
public interface ScooterFeignClient {
    // should we pass the latitude and longitude or the userId?
    List<Scooter> getNearScooters(float latitude, float longitude);

    @GetMapping("/by-kilometers")
    List<Scooter> findAllByKilometers(@RequestParam(required = false) Boolean withPause);
}
