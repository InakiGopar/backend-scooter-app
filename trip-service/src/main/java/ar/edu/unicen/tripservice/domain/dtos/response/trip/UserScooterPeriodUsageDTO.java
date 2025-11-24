package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record UserScooterPeriodUsageDTO(
        Long userId,
        Long scooterId,
        Long uses,
        Double totalKm
) {
}

