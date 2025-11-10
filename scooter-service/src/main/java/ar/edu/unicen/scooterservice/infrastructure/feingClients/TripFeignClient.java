package ar.edu.unicen.scooterservice.infrastructure.feingClients;

import ar.edu.unicen.scooterservice.domain.dtos.response.ScooterTripKMResponseDTO;
import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import ar.edu.unicen.scooterservice.domain.model.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient("name=trip-service,url=http://localhost:8085/api/trip")
public interface TripFeignClient {
}
