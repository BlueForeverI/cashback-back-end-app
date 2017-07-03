package com.cashback.api.viewmodels;

import com.cashback.api.entities.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.Valid;

/**
 * Created by George on 9.5.2017 г..
 */
public class UserViewModel extends BaseViewModel<User, UserViewModel> {

    @Email(message = "Invalid email format")
    private String email;

    @Length(min = 2, message = "The first name should be at least 2 characters")
    private String firstName;

    @Length(min = 2, message = "The first name should be at least 2 characters")
    private String lastName;

    private String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
