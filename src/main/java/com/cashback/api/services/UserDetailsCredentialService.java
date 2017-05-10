package com.cashback.api.services;

import com.cashback.api.entities.User;
import com.cashback.api.repositories.UserRepository;
import com.cashback.api.util.GeneralHelpers;
import com.cashback.api.viewmodels.RegisterViewModel;
import com.cashback.api.viewmodels.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by George on 1.5.2017 г..
 */
@Service
public class UserDetailsCredentialService implements UserDetailsService {

    @Autowired
    private transient UserRepository userRepository;

    @Autowired
    private transient GeneralHelpers helpers;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails foundUser = userRepository.findByEmail(username);

        if (foundUser == null) {
            throw new UsernameNotFoundException("Cannot find user " + username);
        }

        return foundUser;
    }

    public void registerUser(RegisterViewModel userData) {
        User user = userData.toModel();
        user.setActive(true);
        user.setPasswordHash(helpers.encodePassword(userData.getPassword()));
        userRepository.save(user);
    }
}
