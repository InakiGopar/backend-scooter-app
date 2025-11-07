package ar.edu.unicen.tripservice.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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
    private Long stopSartId;
    private Long stopEndId;
    //In Java, for date data on MongoDB use java.util.Date.
    private Date date;
    private Date startDate;
    private Date endDate;
    private Long kmTraveled;
    private Date pause;
    private float totalPrice;
    private String feeId;


    public Trip(Long userId, Long scooterId,
                Long stopSartId, Long stopEndId, Date date,
                Date startDate, Date endDate, Long kmTraveled,
                Date pause, float totalPrice, String feeId) {
        this.userId = userId;
        this.scooterId = scooterId;
        this.stopSartId = stopSartId;
        this.stopEndId = stopEndId;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kmTraveled = kmTraveled;
        this.pause = pause;
        this.totalPrice = totalPrice;
        this.feeId = feeId;
    }
}
