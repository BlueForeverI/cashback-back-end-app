package com.cashback.api.viewmodels;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by George on 9.5.2017 г..
 */
@ApiModel(description = "User data for registration")
public class RegisterViewModel extends UserViewModel {

    @ApiModelProperty(notes = "The user's password", required = true)
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
