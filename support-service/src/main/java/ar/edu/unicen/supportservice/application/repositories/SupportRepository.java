package ar.edu.unicen.supportservice.application.repositories;

import ar.edu.unicen.supportservice.domain.entities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
}
