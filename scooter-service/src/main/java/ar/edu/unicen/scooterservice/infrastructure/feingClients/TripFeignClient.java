package ar.edu.unicen.scooterservice.infrastructure.feingClients;

import ar.edu.unicen.scooterservice.domain.dtos.report.ReportScooterByYearDTO;
import ar.edu.unicen.scooterservice.domain.dtos.report.UserScooterPeriodUsageDTO;
import ar.edu.unicen.scooterservice.domain.model.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="trip-service",url="http://localhost:8085/api/trip")
public interface TripFeignClient {

    //Report A
    @GetMapping("/by-kilometers")
    List<Trip> findAllByKilometers(@RequestParam(value = "withPause", required = false) Boolean withPause);

    //Report C
    @GetMapping("/scooter-by-trips")
    List<ReportScooterByYearDTO> findAllByTravels(@RequestParam int year, @RequestParam int countTrips);
}
