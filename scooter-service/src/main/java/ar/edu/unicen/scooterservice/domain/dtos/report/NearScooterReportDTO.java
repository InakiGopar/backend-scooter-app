package ar.edu.unicen.scooterservice.domain.dtos.report;

public record NearScooterReportDTO(
        Long scooterId,
        double latitude,
        double longitude
) {
}
