package com.randomsturvs.excollabo.validator.model;

import com.randomsturvs.excollabo.validator.constraint.ValidPhoneNumber;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    String userName;

    @ValidPhoneNumber(message = "Phone number is null or invalid")
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
