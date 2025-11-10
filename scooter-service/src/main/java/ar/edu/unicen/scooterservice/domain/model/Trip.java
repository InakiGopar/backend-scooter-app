package ar.edu.unicen.scooterservice.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private Long scooterId;
    private Long tripId;
    private float kilometers;
    private int pause;

}
