package ar.edu.unicen.scooterservice.infrastructure.feingClients;

import ar.edu.unicen.scooterservice.domain.model.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name="trip-service",url="http://localhost:8085/api/trip")
public interface TripFeignClient {

    @GetMapping("/by-kilometers")
    List<Trip> findAllByKilometers(@RequestParam(required = false) Boolean withPause);

    @GetMapping("/scooter-by-trips")
    List<Trip> findAllByTravels(@RequestParam int year, @RequestParam int countTrips);
}
