package com.cashback.api.util;

import com.cashback.api.entities.User;
import com.cashback.api.viewmodels.UserViewModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

/**
 * Created by george on 7/1/2017.
 */
@Service
public class HttpHelper {
    public UserViewModel getLoggedUser(SecurityContext context) {
        Authentication authentication = context.getAuthentication();
        User userCredential = (User) authentication.getPrincipal();
        return new UserViewModel().fromModel(userCredential);
    }
}
