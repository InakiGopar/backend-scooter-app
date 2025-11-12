package ar.edu.unicen.tripservice.application.repositories;

import ar.edu.unicen.tripservice.domain.dtos.response.trip.InvoiceReportResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.ScooterUsageResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripScooterByYearResponseDTO;
import ar.edu.unicen.tripservice.domain.dtos.response.trip.TripScooterUserUsageDTO;
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
    List<ScooterUsageResponseDTO> findAllByKilometersAndPause();

    @Aggregation(pipeline = {
            "{ $group: { _id: '$scooterId', totalKm: { $sum: '$kmTraveled' }, totalTrips: { $sum: 1 } } }",
            "{ $sort: { totalKm: -1 } }"
    })
    List<ScooterUsageResponseDTO> findAllByKilometers();

    @Aggregation(pipeline = {
            "{ $addFields: { year: { $year: '$date' }, month: { $month: '$date' } } }",
            "{ $match: { $and: [ { year: ?0 }, { month: { $gte: ?1, $lte: ?2 } } ] } }",
            "{ $group: { _id: null, totalInvoiced: { $sum: '$totalPrice' }, totalTrips: { $sum: 1 } } }",
            "{ $project: { _id: 0, totalInvoiced: 1, totalTrips: 1 } }"
    })
    InvoiceReportResponseDTO getInvoiceSummaryByYearAndMonthRange(int year, int startMonth, int endMonth);

    @Aggregation(pipeline = {
            "{ $addFields: { year: { $year: '$startTime' } } }",
            "{ $match: { year: ?0 } }",
            "{ $group: { _id: { scooterId: '$scooterId', year: '$year' }, totalTrips: { $sum: '$cantTrips' } } }",
            "{ $match: { totalTrips: { $gt: ?1 } } }",
            "{ $project: { _id: 0, scooterId: '$_id.scooterId', year: '$_id.year', totalTrips: 1 } }"
    })
    List<TripScooterByYearResponseDTO> getScooterByTripInAYear(int year,int cantTrips);

    @Aggregation(pipeline = {
            // 1️⃣ Agrega campos derivados: año y mes desde el Instant
            "{ $addFields: { year: { $year: '$startTime' }, month: { $month: '$startTime' } } }",

            // 2️⃣ Filtra los viajes entre los meses dados (mismo año)
            "{ $match: { month: { $gte: ?0, $lte: ?1 } } }",

            // 3️⃣ Agrupa por usuario y calcula totales
            "{ $group: { _id: '$userId', totalTrips: { $sum: 1 }, totalKm: { $sum: '$kmTraveled' } } }",

            // 4️⃣ Ordena por cantidad de viajes
            "{ $sort: { totalTrips: -1 } }",

            // 5️⃣ Devuelve el resultado formateado
            "{ $project: { _id: 0, userId: '$_id', totalTrips: 1, totalKm: 1 } }"
    })
    List<TripScooterUserUsageDTO> getScooterUserUsage(int startMonth, int endMonth);
}
