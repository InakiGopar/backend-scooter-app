package ar.edu.unicen.scooterservice.domain.dtos.request;

import ar.edu.unicen.scooterservice.domain.entities.Stop;

public record StopRequestDTO(
        Long stopId,
        int latitude,
        int longitude
) {
  public Stop toEntity() {
      return new Stop(
            latitude,
            longitude
      );
  }
}
