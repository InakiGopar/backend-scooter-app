package ar.edu.unicen.scooterservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scooter")
    private Long scooterId;
    private float latitude;
    private float longitude;
    private ScooterState state;
    @ManyToOne
    @JoinColumn(name = "id_stop")
    private Stop currentStop;

    public Scooter(float latitude, float longitude, ScooterState state, Stop currentStop) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
        this.currentStop = currentStop;
    }
}
