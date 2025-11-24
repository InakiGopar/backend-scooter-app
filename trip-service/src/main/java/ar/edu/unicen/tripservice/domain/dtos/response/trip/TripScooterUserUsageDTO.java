package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record TripScooterUserUsageDTO(
    Long userId,
    Long totalScooterUsage,
    Long monthStart,
    Long monthEnd
) {
}