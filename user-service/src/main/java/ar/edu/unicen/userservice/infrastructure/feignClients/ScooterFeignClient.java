package ar.edu.unicen.userservice.infrastructure.feignClients;

import ar.edu.unicen.userservice.domain.dtos.report.NearScooterReportDTO;
import ar.edu.unicen.userservice.domain.dtos.report.ReportADTO;
import ar.edu.unicen.userservice.domain.dtos.report.ReportScooterByYearDTO;
import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="scooter-service", url="http://localhost:8081/api/scooter")
public interface ScooterFeignClient {

    //Report A
    @GetMapping("/by-kilometers")
    List<ReportADTO> getScootersByKilometers(@RequestParam(required = false) Boolean withPause);

    //Report C
    @GetMapping("/by-travels")
    List<ReportScooterByYearDTO> getScootersByTravels(@RequestParam int year, @RequestParam int countTrips);

    // Report G
    @GetMapping("/near-scooters")
    List<NearScooterReportDTO> getNearScooters(@RequestParam float latitude, @RequestParam float longitude);
}
