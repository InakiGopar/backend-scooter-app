package ar.edu.unicen.walletservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private Long walletId;
    @Column(name = "id_account")
    private Long accountId;
    @Column(name = "id_user")
    private Long userId;
    private float amount;

    public Wallet(Long accountId, Long userId, float amount) {
        this.accountId = accountId;
        this.userId = userId;
        this.amount = amount;
    }
}
