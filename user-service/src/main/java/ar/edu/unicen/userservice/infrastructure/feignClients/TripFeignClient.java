package ar.edu.unicen.userservice.infrastructure.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name="trip-service",url="http://localhost:8085/api/trip")
public interface TripFeignClient {
}
