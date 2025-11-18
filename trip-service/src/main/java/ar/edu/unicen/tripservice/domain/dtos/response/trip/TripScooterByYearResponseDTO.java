package ar.edu.unicen.tripservice.domain.dtos.response.trip;

public record TripScooterByYearResponseDTO(
        Long scooterId,
        Integer year,
        Integer totalTrips
) {
}
