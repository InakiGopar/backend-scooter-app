package ar.edu.unicen.scooterservice.application.repositories;

import ar.edu.unicen.scooterservice.domain.entities.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {
}
