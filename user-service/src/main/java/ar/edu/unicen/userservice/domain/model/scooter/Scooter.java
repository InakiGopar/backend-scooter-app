package ar.edu.unicen.userservice.domain.model.scooter;

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
    }

