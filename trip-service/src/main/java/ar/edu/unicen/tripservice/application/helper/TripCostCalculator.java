package ar.edu.unicen.tripservice.application.helper;


import java.time.Duration;
import java.time.Instant;

public class TripCostCalculator {
    private final static int limitPauseMinutes = 15;

    /**
     * Calculates the total trip price based on duration, pause time, and hourly rates.
     * <p>
     * The method computes the total cost between the start and end times of a trip.
     * If the pause duration exceeds the allowed limit, an extra rate is applied
     * for the remaining hours after the pause.
     * </p>
     *
     * @param startTime    the timestamp when the trip started
     * @param endTime      the timestamp when the trip ended
     * @param pauseDuration the total pause duration in minutes during the trip
     * @param pricePerHr   the base hourly price for the trip
     * @param extraPrice   the extra hourly price applied if the pause exceeds the allowed limit
     * @return the total calculated trip cost as a float
     */
    public static float calculateTotalPrice(Instant startTime, Instant endTime, int pauseDuration, Instant endPause,float pricePerHr,  float extraPrice) {

        int hours = Duration.between(startTime, endTime).toHoursPart();

        if(pauseDuration >= limitPauseMinutes){
            int extraHours = Duration.between(endPause, endTime).toHoursPart();
            return (hours * pricePerHr) + (extraHours * extraPrice);
        }

        return (hours * pricePerHr);

    }
}
