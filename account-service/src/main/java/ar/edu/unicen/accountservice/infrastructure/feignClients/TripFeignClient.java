package ar.edu.unicen.accountservice.infrastructure.feignClients;

import ar.edu.unicen.accountservice.domain.model.trip.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="trip-service",url="http://localhost:8085/api/trip")
public interface TripFeignClient {
    @GetMapping("/scooter-user-usage")
    List<Trip> getScooterUserUsage(@RequestParam int monthStart, @RequestParam int monthEnd);
}
