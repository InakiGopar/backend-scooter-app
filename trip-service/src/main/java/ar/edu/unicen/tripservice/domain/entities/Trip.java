package ar.edu.unicen.tripservice.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.Instant;
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
    private Instant startTime;
    private Instant endTime;
    private Long kmTraveled;
    private Instant startPause;
    private Instant endPause;
    private int limitPauseMinutes;
    private float totalPrice;
    private int pauseCount;
    private String feeId;


    public Trip(Long userId, Long scooterId,
                Long stopStartId, Long stopEndId, Date date,
                Instant startTime, Instant endTime,Long kmTraveled,
                Instant startPause,Instant endPause, int limitPauseMinutes,float totalPrice,int pauseCount, String feeId) {
        this.userId = userId;
        this.scooterId = scooterId;
        this.stopStartId = stopStartId;
        this.stopEndId = stopEndId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.kmTraveled = kmTraveled;
        this.startPause = startPause;
        this.endPause = endPause;
        this.limitPauseMinutes = limitPauseMinutes;
        this.totalPrice = totalPrice;
        this.pauseCount = pauseCount;
        this.feeId = feeId;
    }
}
