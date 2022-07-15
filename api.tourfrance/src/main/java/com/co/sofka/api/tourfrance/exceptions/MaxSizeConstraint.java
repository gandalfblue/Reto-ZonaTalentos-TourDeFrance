package com.co.sofka.api.tourfrance.exceptions;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MaxSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSizeConstraint {
    String message() default "El equipo solo puede tener un maximo de 8 ciclistas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
