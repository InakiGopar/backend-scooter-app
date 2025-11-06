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
    @OneToMany(mappedBy = "")
    private List<Scooter> scooterId;
    private int latitude;
    private int longitude;
    //Cambiar a private String street para simplificar logica?

    public Stop(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
