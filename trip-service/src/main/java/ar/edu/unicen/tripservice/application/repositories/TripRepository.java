package ar.edu.unicen.tripservice.application.repositories;

import ar.edu.unicen.tripservice.domain.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {
}
