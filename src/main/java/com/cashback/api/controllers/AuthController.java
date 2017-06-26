package com.cashback.api.controllers;

import com.cashback.api.services.GoogleTokenValidatorService;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
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
    private TokenEndpoint tokenEndpoint;

    private final String googleClient = "client";
    private final String googleSecret = "secret";

    @RequestMapping(path = "/google", method = RequestMethod.POST)
    public Map<String, Object> loginWithGoogle(
            HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        String token = request.getHeader("Authorization");

        try {
            if (this.googleValidationService.isValid(token)) {
                return generateToken(googleClient, googleSecret, request);
            } else {
                response.setStatus(401);
                return null;
            }
        } catch (Exception ex) {
          response.setStatus(401);
          return null;
        }
    }

    private Map<String, Object> generateToken(String client, String secret, HttpServletRequest requestContext) throws IOException, HttpRequestMethodNotSupportedException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String tokenEndpointUrl = requestContext.getScheme() + "://" +
                requestContext.getServerName() + ":" + requestContext.getLocalPort() +
                "/oauth/token";
        HttpPost request = new HttpPost(tokenEndpointUrl);
        StringEntity entity = new StringEntity("grant_type=client_credentials");
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
