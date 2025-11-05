package ar.edu.unicen.walletservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private Role role;
    private String name;
    private String lastName;
    private String email;
    private int phone;
}