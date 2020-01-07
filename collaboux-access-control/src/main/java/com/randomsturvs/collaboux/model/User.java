package com.randomsturvs.collaboux.model;

import com.randomsturvs.collaboux.validator.user.ValidPhoneNumberConstraint;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    String userName;

    @ValidPhoneNumberConstraint(message = "Phone number is null or invalid")
    String phoneNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
