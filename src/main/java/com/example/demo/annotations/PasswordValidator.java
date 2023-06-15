package com.example.demo.annotations;

import com.example.demo.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Escolha uma senha segura";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
