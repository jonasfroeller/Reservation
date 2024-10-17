package org.htblaleonding.jonasfroeller.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.htblaleonding.jonasfroeller.model.Reservation;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeRangeValidator implements ConstraintValidator<TimeRange, Reservation> {

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
        if (reservation.getReservationStart() == null || reservation.getReservationEnd() == null) {
            return true;
        }

        LocalDateTime startDateTime = reservation.getReservationStart().toLocalDate().atStartOfDay();
        LocalDateTime endDateTime = reservation.getReservationEnd().toLocalDate().atStartOfDay();

        LocalTime startTime = startDateTime.toLocalTime();
        LocalTime endTime = endDateTime.toLocalTime();
        LocalTime eightAM = LocalTime.of(8, 0);
        LocalTime eightPM = LocalTime.of(20, 0);

        return !startTime.isBefore(eightAM) && !endTime.isAfter(eightPM);
    }
}
