package ar.edu.unicen.accountservice.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AccountUserID implements Serializable {
    private Long accountId;
    private Long userId;
}
