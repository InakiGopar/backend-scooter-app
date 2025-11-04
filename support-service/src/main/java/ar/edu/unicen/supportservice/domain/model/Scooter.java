package ar.edu.unicen.supportservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scooter {
    private Long scooterId;
    private ScooterState state;
}
