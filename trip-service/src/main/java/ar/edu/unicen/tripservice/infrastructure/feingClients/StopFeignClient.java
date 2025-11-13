package ar.edu.unicen.tripservice.infrastructure.feingClients;

import ar.edu.unicen.tripservice.domain.model.scooter.Stop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "scooter-service", url = "http://localhost:8081/api/stop")
public interface StopFeignClient {

    @GetMapping("/{stopId}")
    Stop getStopById(Long stopId);
}
