package ar.edu.unicen.userservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;
    private Role role;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private int phone;
}
