package ar.edu.unicen.tripservice.domain.model.scooter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scooter {
    private Long scooterId;
    private float latitude;
    private float longitude;
    private ScooterState state;
    private Stop currentStop;
    private float kilometers;

    //Verify if this constructor is necessary.
    public Scooter(Long scooterId, ScooterState state, Stop currentStop, float kilometers) {
        this.scooterId = scooterId;
        this.state = state;
        this.currentStop = currentStop;
        this.kilometers = kilometers;
    }
    public Scooter(Long scooterId, ScooterState state, Stop currentStop) {
        this.scooterId = scooterId;
        this.state = state;
        this.currentStop = currentStop;
    }
    //Check if it is necessary to convert this class into a record and rename it to ScooterDTO.
}
