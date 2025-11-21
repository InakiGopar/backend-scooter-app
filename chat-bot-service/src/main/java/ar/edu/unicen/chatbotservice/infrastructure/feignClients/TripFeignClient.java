package ar.edu.unicen.chatbotservice.infrastructure.feignClients;

import ar.edu.unicen.chatbotservice.dtos.response.trip.TripResponseDTO;
import ar.edu.unicen.chatbotservice.dtos.response.trip.TripStatsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="trip-service",url="http://localhost:8085/api")
public interface TripFeignClient {

    @GetMapping("trip/{tripId}")
    TripResponseDTO findTripById(@PathVariable String tripId);

    @GetMapping("trip/stats/{userId}")
    TripStatsDTO getTripsStats(@PathVariable Long userId);
}
