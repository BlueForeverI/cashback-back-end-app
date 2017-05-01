package com.cashback.api.services;

import com.cashback.api.repositories.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails foundUser = userRepository.findByEmail(username);

        if (foundUser == null) {
            throw new UsernameNotFoundException("Cannot find user " + username);
        }

        return foundUser;
    }
}
