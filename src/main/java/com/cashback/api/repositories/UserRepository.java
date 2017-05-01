package com.cashback.api.repositories;

import com.cashback.api.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by George on 1.5.2017 г..
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByEmail(String email);
}
