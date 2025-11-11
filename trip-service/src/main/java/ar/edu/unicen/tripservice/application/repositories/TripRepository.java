package ar.edu.unicen.tripservice.application.repositories;

import ar.edu.unicen.tripservice.domain.dtos.response.trip.ScooterUsageResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripScooterByYearResponseDTO;
import ar.edu.unicen.tripservice.domain.entities.Trip;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {
    @Aggregation(pipeline = {
            "{ $match: { pauseMinutes: { $exists: true } } }",
            "{ $group: { _id: '$scooterId', totalKm: { $sum: '$kmTraveled' }, totalPauses: { $sum: '$pauseCount' }, totalTrips: { $sum: 1 } } }",
            "{ $sort: { totalKm: -1 } }"
    })
    List<ScooterUsageResponseDTO> findAllByKilometersAndPause();

    @Aggregation(pipeline = {
            "{ $group: { _id: '$scooterId', totalKm: { $sum: '$kmTraveled' }, totalTrips: { $sum: 1 } } }",
            "{ $sort: { totalKm: -1 } }"
    })
    List<ScooterUsageResponseDTO> findAllByKilometers();

    @Aggregation(pipeline = {
            "{ $addFields: { year: { $year: '$startTime' } } }",
            "{ $match: { year: ?0 } }",
            "{ $group: { _id: '$scooterId', totalTrips: { $sum: '$cantTrips' } } }",
            "{ $match: { totalTrips: { $gt: ?1 } } }",
            "{ $project: { _id: 0, scooterId: '$_id', year: '$year', totalTrips: 1 } }"
    })
    List<TripScooterByYearResponseDTO> getScooterByTripInAYear(int year,int cantTrips);
}
