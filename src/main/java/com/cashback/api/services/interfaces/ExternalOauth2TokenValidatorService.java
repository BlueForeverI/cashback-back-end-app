package com.cashback.api.services.interfaces;

/**
 * Created by george on 6/26/2017.
 */
public interface ExternalOauth2TokenValidatorService {
    boolean isValid(String token);
    String getValidationUrl();
}
