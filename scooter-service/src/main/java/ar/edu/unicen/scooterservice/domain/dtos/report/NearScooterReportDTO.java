package ar.edu.unicen.scooterservice.domain.dtos.report;

public record NearScooterReportDTO(
        Long scooterId,
        float latitude,
        float longitude
) {
}
