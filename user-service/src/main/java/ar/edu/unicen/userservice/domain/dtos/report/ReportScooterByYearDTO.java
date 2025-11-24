package ar.edu.unicen.userservice.domain.dtos.report;

public record ReportScooterByYearDTO(
        Long scooterId,
        Integer year,
        Integer totalTrips
) {
}
