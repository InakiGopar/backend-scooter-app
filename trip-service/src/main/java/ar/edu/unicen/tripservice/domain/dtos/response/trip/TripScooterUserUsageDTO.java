package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record TripScooterUserUsageDTO(
    Long userId,
    int countScooterUsage,
    int monthStart,
    int monthEnd
) {
}