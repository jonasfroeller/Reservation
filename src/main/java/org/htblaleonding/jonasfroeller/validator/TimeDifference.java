package org.htblaleonding.jonasfroeller.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeDifferenceValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeDifference {
    String message() default "Reservation duration must be exactly 30 minutes.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
