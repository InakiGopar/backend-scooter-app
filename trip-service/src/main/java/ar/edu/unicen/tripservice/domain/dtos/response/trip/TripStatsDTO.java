package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record TripStatsDTO(
        String name,
        float totalSpent,
        Long totalKm,
        Long scooterUsed,
        TripResponseDTO longestTrip,
        TripResponseDTO mostExpensiveTrip
) {
}
