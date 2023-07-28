package com.example.demotaskitbootcamp.web.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LatinLettersStringValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyLatinLetters {
  String message() default "Invalid value. The field must contain only latin letters";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
