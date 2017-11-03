package com.cashback.api.controllers;

import com.cashback.api.responses.BaseResponse;
import com.cashback.api.responses.ErrorResponse;
import com.cashback.api.responses.SuccessResponse;
import com.cashback.api.services.UserDetailsCredentialService;
import com.cashback.api.util.HttpHelper;
import com.cashback.api.viewmodels.RegisterViewModel;
import com.cashback.api.viewmodels.UserViewModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by George on 9.5.2017 г..
 */

@RestController
@PreAuthorize("!isAnonymous()")
@RequestMapping("/api/user")
public class UsersController {

    private UserDetailsCredentialService usersService;
    private HttpHelper httpHelper;

    @Autowired
    public UsersController(
            UserDetailsCredentialService usersService,
            HttpHelper httpHelper) {
        this.usersService = usersService;
        this.httpHelper = httpHelper;
    }

    @ApiOperation(value = "Register a new user", notes = "Register a new user")
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public BaseResponse<Boolean> register(
            @ApiParam(value = "The user data", required = true)
            @RequestBody @Valid RegisterViewModel user) {

        try {
            usersService.registerUser(user);
            return new SuccessResponse<>(true);
        } catch (Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @ApiOperation(value = "Get all users", notes = "Get all users")
    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse<List<UserViewModel>> getAllUsers() {
        try {
            return new SuccessResponse<>(usersService.getAllUsers());
        } catch(Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a user by ID", notes = "Gets a user by ID")
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public BaseResponse<UserViewModel> getUserById(
            @ApiParam(value = "The user ID", required = true)
            @PathVariable Long id) {
        try {
            return new SuccessResponse<>(usersService.getUserById(id));
        } catch(Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets the logged user information", notes = "Gets the logged user information")
    @RequestMapping(method = RequestMethod.GET, path = "/me")
    public BaseResponse<UserViewModel> getUserData() {
        try {
            return new SuccessResponse<>(httpHelper.getLoggedUser(SecurityContextHolder.getContext()));
        } catch(Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @ApiOperation(value = "Update user data", notes = "Update user data")
    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse<Void> updateUser(
            @ApiParam(value = "The user data", required = true)
            @RequestBody @Valid UserViewModel user) {
        try {
            usersService.updateUser(user);
            return new SuccessResponse<>(null);
        } catch (Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete a user by ID", notes = "Delete a user by ID")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public BaseResponse<Void> deleteUser(
            @ApiParam(value = "The user ID", required = true)
            @PathVariable Long id) {
        try {
            usersService.deleteUser(id);
            return new SuccessResponse<>(null);
        } catch (Exception ex) {
            return new ErrorResponse<>(ex.getMessage());
        }
    }
}
