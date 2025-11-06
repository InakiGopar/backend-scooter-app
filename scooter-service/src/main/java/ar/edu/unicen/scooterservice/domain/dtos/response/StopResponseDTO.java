package ar.edu.unicen.scooterservice.domain.dtos.response;

import ar.edu.unicen.scooterservice.domain.entities.Stop;

public record StopResponseDTO(
        Long stopId,
        int latitude,
        int longitude
) {
    public static StopResponseDTO toDTO(Stop stop) {
        return new StopResponseDTO(
                stop.getStopId(),
                stop.getLatitude(),
                stop.getLongitude()
        );
    }
}
