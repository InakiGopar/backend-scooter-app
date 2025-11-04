package ar.edu.unicen.tripservice.application.repositories;

import ar.edu.unicen.tripservice.domain.entities.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
}
