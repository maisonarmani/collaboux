package com.randomsturvs.collaboux.validators;

import com.randomsturvs.collaboux.validators.constraint.ValidPhoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumberConstraint, String> {

    String message;

    @Override
    public void initialize(ValidPhoneNumberConstraint validPhoneNumberConstraint) {
        message = validPhoneNumberConstraint.message();
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }

}
