package com.randomsturvs.excollabo.validator;

import com.randomsturvs.excollabo.validator.constraint.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    String message;

    @Override
    public void initialize(ValidPhoneNumber contactNumber) {
        message = contactNumber.message();
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }

}
