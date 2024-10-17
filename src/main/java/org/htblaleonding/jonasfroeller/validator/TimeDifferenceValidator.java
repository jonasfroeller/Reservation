package org.htblaleonding.jonasfroeller.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.htblaleonding.jonasfroeller.model.Reservation;

import java.time.Duration;

public class TimeDifferenceValidator implements ConstraintValidator<TimeDifference, Reservation> {

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
        if (reservation.getReservationStart() == null || reservation.getReservationEnd() == null) {
            return true;
        }
        Duration duration = Duration.between(reservation.getReservationStart().toInstant(), reservation.getReservationEnd().toInstant());
        return duration.toMinutes() == 30;
    }
}
