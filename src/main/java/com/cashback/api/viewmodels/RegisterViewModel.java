package com.cashback.api.viewmodels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by George on 9.5.2017 г..
 */
public class RegisterViewModel extends UserViewModel {

    @NotNull
    @Length(min = 6, message = "The password should be at least 6 characters")
    private String password;

    public RegisterViewModel() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
