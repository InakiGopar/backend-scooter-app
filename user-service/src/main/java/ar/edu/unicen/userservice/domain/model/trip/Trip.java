package ar.edu.unicen.userservice.domain.model.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private Long  scooterId;
    private float totalKilometers;
    private int totalPausesMinutes;
    private int totalTrips;
}
