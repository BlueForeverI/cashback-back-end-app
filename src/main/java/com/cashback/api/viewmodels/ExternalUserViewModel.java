package com.cashback.api.viewmodels;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by george on 7/1/2017.
 */
@ApiModel(description = "User data that comes from an external system (Google, etc)")
public class ExternalUserViewModel {

    @ApiModelProperty(notes = "The user email from the external system")
    private String email;

    @ApiModelProperty(notes = "The external user ID")
    private String id;

    @ApiModelProperty(notes = "User first name")
    private String firstName;

    @ApiModelProperty(notes = "User first name")
    private String lastName;

    @ApiModelProperty(notes = "The user avatar URL")
    private String profilePictureUrl;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @JsonGetter("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    @Override
    public String toString() {
        return "ExternalUserViewModel{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                '}';
    }
}
