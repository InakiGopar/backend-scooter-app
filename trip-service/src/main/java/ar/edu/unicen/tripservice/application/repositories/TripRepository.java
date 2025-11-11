package ar.edu.unicen.tripservice.application.repositories;

import ar.edu.unicen.tripservice.domain.dtos.response.trip.ScooterUsageResponseDTO;
import ar.edu.unicen.tripservice.domain.entities.Trip;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {
    @Aggregation(pipeline = {
            "{ $match: { pauseMinutes: { $exists: true } } }",
            "{ $group: { _id: '$scooterId', totalKm: { $sum: '$kmTraveled' }, totalPauses: { $sum: '$pauseCount' }, totalTrips: { $sum: 1 } } }",
            "{ $sort: { totalKm: -1 } }"
    })
    List<ScooterUsageResponseDTO> findAllByPause();

    @Aggregation(pipeline = {
            "{ $group: { _id: '$scooterId', totalKm: { $sum: '$kmTraveled' }, totalTrips: { $sum: 1 } } }",
            "{ $sort: { totalKm: -1 } }"
    })
    List<ScooterUsageResponseDTO> findAllByKilometers();

    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    List<Trip> findByDateBetween(Date startDate, Date endDate);
}
