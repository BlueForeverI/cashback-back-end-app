package com.cashback.api.viewmodels;

import com.cashback.api.entities.User;

import javax.persistence.Column;

/**
 * Created by George on 9.5.2017 г..
 */
public class UserViewModel extends BaseViewModel<User, UserViewModel> {
    private String email;
    private String firstName;
    private String lastName;

    public UserViewModel() {
        super(User.class, UserViewModel.class);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
