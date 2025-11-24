package ar.edu.unicen.scooterservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private double latitude;
    private double longitude;


    public Stop(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
