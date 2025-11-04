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
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stop")
    private Long stopId;
    @ManyToOne
    @JoinColumn(name = "id_scooter")
    private Scooter scooterId;
    private int latitude;
    private int longitude;
}
