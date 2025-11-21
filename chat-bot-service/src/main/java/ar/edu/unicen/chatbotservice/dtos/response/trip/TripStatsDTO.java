package ar.edu.unicen.chatbotservice.dtos.response.trip;

public record TripStatsDTO(
        String userName,
        float totalSpent,
        Long totalKm,
        Long scooterUsed,
        TripResponseDTO longestTrip,
        TripResponseDTO mostExpensiveTrip
) {
}
