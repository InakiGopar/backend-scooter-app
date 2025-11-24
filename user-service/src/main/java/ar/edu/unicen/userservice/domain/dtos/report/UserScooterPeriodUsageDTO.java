package ar.edu.unicen.userservice.domain.dtos.report;

public record UserScooterPeriodUsageDTO(
        Long userId,
        Long scooterId,
        Long uses,
        Double totalKm
) {
}
