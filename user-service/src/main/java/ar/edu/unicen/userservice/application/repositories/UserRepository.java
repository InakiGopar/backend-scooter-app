package ar.edu.unicen.userservice.application.repositories;

import ar.edu.unicen.userservice.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
