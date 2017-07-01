package com.cashback.api.services;

import com.cashback.api.exceptions.ExternalOauth2TokenException;
import com.cashback.api.services.interfaces.ExternalOauth2TokenValidatorService;
import com.cashback.api.viewmodels.ExternalUserViewModel;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by george on 6/26/2017.
 */
@Service
public class GoogleTokenValidatorService implements ExternalOauth2TokenValidatorService {
    @Override
    public ExternalUserViewModel getUserInfo(String token) throws IOException, ExternalOauth2TokenException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(getValidationUrl());
        request.addHeader("Content-type", "application/json");
        request.addHeader("Authorization", token);
        HttpResponse response = httpClient.execute(request);

        Gson gson = new Gson();
        Map<String, Object> parsed = gson.fromJson(
                EntityUtils.toString(response.getEntity(), "UTF-8"), Map.class);

        if(parsed.containsKey("error")) {
            throw new ExternalOauth2TokenException();
        }

        ExternalUserViewModel googleUser = new ExternalUserViewModel();
        googleUser.setEmail(((ArrayList<Map<String, String>>)parsed.get("emails")).get(0).get("value"));
        googleUser.setId(parsed.get("id").toString());

        return googleUser;
    }

    @Override
    public String getValidationUrl() {
        return "https://www.googleapis.com/plus/v1/people/me";
    }
}
