package com.cashback.api.controllers;

import com.cashback.api.services.FacebookTokenValidatorService;
import com.cashback.api.services.GoogleTokenValidatorService;
import com.cashback.api.services.UserDetailsCredentialService;
import com.cashback.api.viewmodels.ExternalUserViewModel;
import com.cashback.api.viewmodels.RegisterViewModel;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * Created by george on 6/26/2017.
 */
@RestController
@RequestMapping(path = "/oauth/token")
public class AuthController {

    @Autowired
    private GoogleTokenValidatorService googleValidationService;

    @Autowired
    private FacebookTokenValidatorService facebookTokenValidatorService;

    @Autowired
    private UserDetailsCredentialService usersService;

    private final String googleClient = "google-auth-client";
    private final String googleSecret = "IqQWfOAHhs55qn0w";

    private final String facebookClient = "facebook-auth-client";
    private final String facebookSecret = "L3VLXAKJxYUVO8C0";

    @RequestMapping(path = "/google", method = RequestMethod.GET)
    public Map<String, Object> loginWithGoogle(
            HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");

        try {
            ExternalUserViewModel googleUser = googleValidationService.getUserInfo(token);
            return generateToken(googleUser.getEmail(), googleUser.getId(),
                    googleClient, googleSecret, request);
        } catch (Exception ex) {
          response.setStatus(401);
          return null;
        }
    }

    @RequestMapping(path = "/facebook", method = RequestMethod.GET)
    public Map<String, Object> loginRegisterWithFacebook(
            HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");

        try {
            ExternalUserViewModel externalUser = facebookTokenValidatorService.getUserInfo(token);
            String username = String.format("facebook-%s", externalUser.getId());
            if(usersService.loadUserByUsername(username) == null) {
                RegisterViewModel registerVm = new RegisterViewModel() {{
                    setUsername(username);
                    setEmail(String.format("%s.%s@facebook.com",
                            externalUser.getFirstName(), externalUser.getLastName()));
                    setFirstName(externalUser.getFirstName());
                    setLastName(externalUser.getLastName());
                    setImageUrl(externalUser.getProfilePictureUrl());
                    setPassword(externalUser.getId());
                }};

                usersService.registerUser(registerVm);
            }

            return generateToken(username, externalUser.getId(), facebookClient, facebookSecret, request);
        } catch (Exception ex) {
            response.setStatus(401);
            return null;
        }
    }

    private Map<String, Object> generateToken(String username,
                                              String password,
                                              String client,
                                              String secret,
                                              HttpServletRequest requestContext)
            throws IOException, HttpRequestMethodNotSupportedException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String tokenEndpointUrl = requestContext.getScheme() + "://" +
                requestContext.getServerName() + ":" + requestContext.getLocalPort() +
                "/oauth/token";
        HttpPost request = new HttpPost(tokenEndpointUrl);

        String requestEntity = String.format("grant_type=%s&username=%s&password=%s",
                "password", username, password);

        StringEntity entity = new StringEntity(requestEntity);
        request.setEntity(entity);
        request.setHeader("Content-type", "application/x-www-form-urlencoded");
        request.setHeader("Accept", "application/json");
        String rawAuthHeader = client + ":" + secret;
        String encodedAuthHeader = new String(Base64.getEncoder().encode(rawAuthHeader.getBytes()));
        request.setHeader("Authorization", "Basic " + encodedAuthHeader);

        HttpResponse response = httpClient.execute(request);
        Gson gson = new Gson();
        Map<String, Object> tokenResponse = gson.fromJson(
                EntityUtils.toString(response.getEntity(), "UTF-8"), Map.class);

        httpClient.close();
        return tokenResponse;
    }
}
