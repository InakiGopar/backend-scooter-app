package ar.edu.unicen.accountservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountUser {
    /*
        TO-DO:
    * hay que hacer la clave primaria compuesta
    * */
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private AccountUserID id;


}
