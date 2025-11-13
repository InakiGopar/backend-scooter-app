package ar.edu.unicen.tripservice.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.Instant;
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
    private Instant startTime;
    private Instant endTime;
    private Long kmTraveled;
    private Instant startPause;
    private Instant endPause;
    private float totalPrice;
    private int pauseCount;
    private String feeId;


    public Trip(Long userId, Long scooterId,
                Long stopStartId, Long stopEndId,
                Long kmTraveled, String feeId) {
        this.userId = userId;
        this.scooterId = scooterId;
        this.stopStartId = stopStartId;
        this.stopEndId = stopEndId;
        this.kmTraveled = kmTraveled;
        this.feeId = feeId;
    }
}
