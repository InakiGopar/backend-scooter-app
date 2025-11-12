package ar.edu.unicen.userservice.infrastructure.feignClients;

import ar.edu.unicen.userservice.domain.dtos.report.NearScooterReportDTO;
import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name="scooter-service", url="http://localhost:8081/scooter")
public interface ScooterFeignClient {

    //Report A
    //Should we change Scooter to a new DTO response?
    @GetMapping("/by-kilometers")
    List<Scooter> getScootersByKilometers(@RequestParam(required = false) Boolean withPause);

    //Report C
    //Should we change Scooter to a new DTO response?
    @GetMapping("/by-travels")
    List<Scooter> getScootersByTravels(@RequestParam int year, @RequestParam int countTrips);

    // Report G
    @GetMapping("/near-scooters")
    List<NearScooterReportDTO> getNearScooters(@RequestParam float latitude, @RequestParam float longitude);
}
