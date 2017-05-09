package com.cashback.api.viewmodels;

/**
 * Created by George on 9.5.2017 г..
 */
public class RegisterViewModel extends UserViewModel {
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
