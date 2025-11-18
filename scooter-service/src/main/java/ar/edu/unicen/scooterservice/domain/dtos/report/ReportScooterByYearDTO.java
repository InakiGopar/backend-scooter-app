package ar.edu.unicen.scooterservice.domain.dtos.report;

public record ReportScooterByYearDTO(
        Long scooterId,
        Integer year,
        Integer totalTrips
) {
}
