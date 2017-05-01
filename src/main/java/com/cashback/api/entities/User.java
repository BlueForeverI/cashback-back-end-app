package com.cashback.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by George on 1.5.2017 г..
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
