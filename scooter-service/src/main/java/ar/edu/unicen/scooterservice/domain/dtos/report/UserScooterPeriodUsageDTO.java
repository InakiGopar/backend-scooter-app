package ar.edu.unicen.scooterservice.domain.dtos.report;

public record UserScooterPeriodUsageDTO(
        Long userId,
        Long scooterId,
        Long uses
) {
}
