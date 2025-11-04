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
    private Long idWallet;
    @Column(name = "id_account")
    private Long idAccount;
    @Column(name = "id_user")
    private Long idUser;
    private float amount;
}
