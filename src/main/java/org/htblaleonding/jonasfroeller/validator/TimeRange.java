package org.htblaleonding.jonasfroeller.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeRange {
    String message() default "Reservation times must be within the range of 8:00 AM to 8:00 PM.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
