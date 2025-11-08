package ar.edu.unicen.tripservice.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Document(collection = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @Field(targetType = FieldType.STRING)
    private UUID tripId = UUID.randomUUID();
    private Long userId;
    private Long scooterId;
    private Long stopStartId;
    private Long stopEndId;
    //In Java, for date data on MongoDB use java.util.Date.
    private Date date;
    private int tripHours;
    private Long kmTraveled;
    private int pause;
    private float totalPrice;
    private String feeId;


    public Trip(Long userId, Long scooterId,
                Long stopStartId, Long stopEndId, Date date,
                 int tripHours, Long kmTraveled,
                int pause, float totalPrice, String feeId) {
        this.userId = userId;
        this.scooterId = scooterId;
        this.stopStartId = stopStartId;
        this.stopEndId = stopEndId;
        this.date = date;
        this.tripHours = tripHours;
        this.kmTraveled = kmTraveled;
        this.pause = pause;
        this.totalPrice = totalPrice;
        this.feeId = feeId;
    }
}
