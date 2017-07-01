package com.cashback.api.controllers;

import com.cashback.api.responses.BaseResponse;
import com.cashback.api.responses.ErrorResponse;
import com.cashback.api.responses.SuccessResponse;
import com.cashback.api.services.UserDetailsCredentialService;
import com.cashback.api.util.HttpHelper;
import com.cashback.api.viewmodels.RegisterViewModel;
import com.cashback.api.viewmodels.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

/**
 * Created by George on 9.5.2017 г..
 */

@RestController
@PreAuthorize("!isAnonymous()")
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UserDetailsCredentialService usersService;

    @Autowired
    private HttpHelper httpHelper;

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

    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse<List<UserViewModel>> getAllUsers() {
        try {
            UserViewModel user = httpHelper.getLoggedUser(SecurityContextHolder.getContext());
            return new SuccessResponse<>(usersService.getAllUsers());
        } catch(Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public BaseResponse<UserViewModel> getUserById(@PathVariable Long id) {
        try {
            return new SuccessResponse<>(usersService.getUserById(id));
        } catch(Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse<Void> updateUser(@RequestBody @Valid UserViewModel user) {
        try {
            usersService.updateUser(user);
            return new SuccessResponse<>(null);
        } catch (Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable Long id) {
        try {
            usersService.deleteUser(id);
            return new SuccessResponse<>(null);
        } catch (Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }
}
