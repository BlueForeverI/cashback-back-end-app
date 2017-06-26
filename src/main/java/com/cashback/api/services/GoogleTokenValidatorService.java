package com.cashback.api.services;

import com.cashback.api.services.interfaces.ExternalOauth2TokenValidatorService;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by george on 6/26/2017.
 */
@Service
public class GoogleTokenValidatorService implements ExternalOauth2TokenValidatorService {
    @Override
    public boolean isValid(String token) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(getValidationUrl());
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", token);
            HttpResponse response = httpClient.execute(request);

            Gson gson = new Gson();
            Map<String, Object> parsed = gson.fromJson(
                    EntityUtils.toString(response.getEntity(), "UTF-8"), Map.class);

            if(parsed.containsKey("error")) {
                return false;
            } else {
                return true;
            }

        } catch (IOException ex) {

        }

        return false;
    }

    @Override
    public String getValidationUrl() {
        return "https://www.googleapis.com/plus/v1/people/me";
    }
}
