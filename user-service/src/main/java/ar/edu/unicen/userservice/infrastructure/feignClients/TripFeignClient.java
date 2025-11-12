package ar.edu.unicen.userservice.infrastructure.feignClients;

import ar.edu.unicen.userservice.domain.model.trip.Trip;
import ar.edu.unicen.userservice.domain.model.trip.report.InvoiceReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name="trip-service",url="http://localhost:8085/api/trip")
public interface TripFeignClient {
@GetMapping("/kilometers")
List<Trip> getTripsWithPause(@RequestParam String kilometers);

    @GetMapping("/total-invoice")
    InvoiceReport getTotalInvoice(
            @RequestParam("year") int year,
            @RequestParam("startMonth") int startMonth,
            @RequestParam("endMonth") int endMonth
    );


}
