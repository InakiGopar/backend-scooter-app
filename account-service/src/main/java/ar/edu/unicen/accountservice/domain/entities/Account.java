package ar.edu.unicen.accountservice.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "id_account")
    private Long accountId;
    @Column(name = "account_name")
    private String Accountname;
    private float amount;
    private AccountState state;
}
