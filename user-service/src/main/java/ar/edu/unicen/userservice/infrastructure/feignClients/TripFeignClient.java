package ar.edu.unicen.userservice.infrastructure.feignClients;

import ar.edu.unicen.userservice.domain.dtos.report.FeeResponseDTO;
import ar.edu.unicen.userservice.domain.dtos.report.UserScooterPeriodUsageDTO;
import ar.edu.unicen.userservice.domain.model.trip.Fee;
import ar.edu.unicen.userservice.domain.model.trip.Trip;
import ar.edu.unicen.userservice.domain.dtos.report.InvoiceReportDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="trip-service",url="http://localhost:8085/api")
public interface TripFeignClient {

@GetMapping("/trip/kilometers")
List<Trip> getTripsWithPause(@RequestParam String kilometers);

//Report D
    @GetMapping("/trip/total-invoice")
    InvoiceReportDTO getTotalInvoice(
            @RequestParam("year") int year,
            @RequestParam("startMonth") int startMonth,
            @RequestParam("endMonth") int endMonth
    );

    //Report F
    @PostMapping("/fee")
    FeeResponseDTO createFee(@RequestBody Fee requestFee);


    //Report H (two methods)
    @GetMapping("/trip/scooter-usages-by-period/{userId}")
    List<UserScooterPeriodUsageDTO> getScooterUsesByPeriod(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int monthStart,
            @RequestParam int monthEnd
    );

    @GetMapping("/trip/scooter-usages-by-period")
    List<UserScooterPeriodUsageDTO> getUsagePeriodForUsersByAccount(
            @RequestParam List<Long> userIds,
            @RequestParam int year,
            @RequestParam int monthStart,
            @RequestParam int monthEnd
    );




}
