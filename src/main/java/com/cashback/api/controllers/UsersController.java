package com.cashback.api.controllers;

import com.cashback.api.responses.BaseResponse;
import com.cashback.api.responses.ErrorResponse;
import com.cashback.api.responses.SuccessResponse;
import com.cashback.api.services.UserDetailsCredentialService;
import com.cashback.api.viewmodels.RegisterViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by George on 9.5.2017 г..
 */

@RestController
@PreAuthorize("!isAnonymous()")
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UserDetailsCredentialService usersService;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public BaseResponse<Boolean> register(@RequestBody @Valid RegisterViewModel user) {

        try {
            usersService.registerUser(user);
            return new SuccessResponse<>(true);
        } catch (Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }
}
