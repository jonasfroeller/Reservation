package org.htblaleonding.jonasfroeller.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.htblaleonding.jonasfroeller.model.Reservation;

import java.time.LocalTime;

public class TimeRangeValidator implements ConstraintValidator<TimeRange, Reservation> {

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
        if (reservation.getReservationStart() == null || reservation.getReservationEnd() == null) {
            return true;
        }

        LocalTime startTime = reservation.getReservationStart().toLocalTime();
        LocalTime endTime = reservation.getReservationEnd().toLocalTime();

        return !startTime.isBefore(LocalTime.of(8, 0)) && !endTime.isAfter(LocalTime.of(20, 0));
    }
}
