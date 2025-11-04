package ar.edu.unicen.tripservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trip")
    private Long tripId;
    @Column(name = "id_user")
    private Long userId;
    @Column(name = "id_scooter")
    private Long scooterId;
    @Column(name = "id_stop_start")
    private Long stopSartId;
    @Column(name = "id_stop_end")
    private Long stopEndId;
    //En Java, para los datos de fecha en MongoDB se utiliza la clase java.util.Date.
    private Date date;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "km_traveled")
    private Long kmTraveled;
    private Date pause;
    @Column(name = "total_price")
    private float totalPrice;
}
