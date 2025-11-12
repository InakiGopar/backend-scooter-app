package ar.edu.unicen.userservice.domain.dtos.report;

public record NearScooterReportDTO(
        Long scooterId,
        float latitude,
        float longitude
) {
}
