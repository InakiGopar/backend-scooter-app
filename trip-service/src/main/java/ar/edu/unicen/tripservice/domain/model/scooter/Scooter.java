package ar.edu.unicen.tripservice.domain.model.scooter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scooter {
    private Long scooterId;
    private ScooterState state;
    private Stop currentStop;

    public Scooter(Long scooterId, ScooterState state) {
        this.scooterId = scooterId;
        this.state = state;
    }
}
