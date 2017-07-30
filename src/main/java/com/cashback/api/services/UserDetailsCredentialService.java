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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

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
        UserDetails foundUser = userRepository.findByUsername(username);

        return foundUser;
    }

    public void registerUser(RegisterViewModel userData) {
        if(userData.getUsername() == null) {
            userData.setUsername(userData.getEmail());
        }

        User user = userData.toModel();
        user.setActive(true);
        user.setPasswordHash(helpers.encodePassword(userData.getPassword()));
        userRepository.save(user);
    }

    public List<UserViewModel> getAllUsers() {
        Iterable<User> allUsers =  userRepository.findAll();
        return StreamSupport.stream(allUsers.spliterator(), false)
                .map(x -> new UserViewModel().fromModel(x))
                .collect(toList());
    }

    public void updateUser(UserViewModel userVm) {
        User user = userVm.toModel();
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    public UserViewModel getUserById(Long id) {
        return new UserViewModel().fromModel(userRepository.findOne(id));
    }
}
