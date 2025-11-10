package ar.edu.unicen.scooterservice.application.repositories;

import ar.edu.unicen.scooterservice.domain.dtos.response.ScooterResponseDTO;
import ar.edu.unicen.scooterservice.domain.dtos.response.ScooterTripKMResponseDTO;
import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
}
