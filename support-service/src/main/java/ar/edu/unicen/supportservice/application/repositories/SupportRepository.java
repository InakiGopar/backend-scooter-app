package ar.edu.unicen.supportservice.application.repositories;

import ar.edu.unicen.supportservice.domain.entities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    List<Support> findByStartDate(Date startDate);
}
