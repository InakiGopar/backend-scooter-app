package ar.edu.unicen.scooterservice.application.repositories;

import ar.edu.unicen.scooterservice.domain.dtos.report.NearScooterReportDTO;
import ar.edu.unicen.scooterservice.domain.entities.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
    //Report G
    @Query("""
            SELECT new ar.edu.unicen.scooterservice.domain.dtos.report.NearScooterReportDTO(
                s.scooterId, s.latitude, s.longitude)
            FROM Scooter s
            WHERE (6371 * acos(
                cos(radians(:latitude)) * cos(radians(s.latitude)) *
                cos(radians(s.longitude) - radians(:longitude)) +
                sin(radians(:latitude)) * sin(radians(s.latitude))
            )) < :radius
    """)
    List<NearScooterReportDTO> getByLatitudeAndLongitude(double latitude, double longitude, double radius);
}
