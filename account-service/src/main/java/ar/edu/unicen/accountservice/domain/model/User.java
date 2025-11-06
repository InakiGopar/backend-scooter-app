package ar.edu.unicen.accountservice.domain.model;
import lombok.*;

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