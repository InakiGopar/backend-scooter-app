package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record UserPeriodUsageResponseDTO(
        Long userId,
        int totalTrips,
        long totalKm,
        double totalDurationMinutes
) {
}

