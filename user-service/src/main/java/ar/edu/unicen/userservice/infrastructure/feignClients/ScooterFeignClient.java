package ar.edu.unicen.userservice.infrastructure.feignClients;

import ar.edu.unicen.userservice.domain.model.scooter.Scooter;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name="scooter-service", url="http://localhost:8081/scooter")
public interface ScooterFeignClient {
    // should we pass the latitude and longitude or the userId?
    List<Scooter> getNearScooters(float latitude, float longitude);
}
