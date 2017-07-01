package com.cashback.api.services.interfaces;

import com.cashback.api.exceptions.ExternalOauth2TokenException;
import com.cashback.api.viewmodels.ExternalUserViewModel;

import java.io.IOException;
import java.util.Map;

/**
 * Created by george on 6/26/2017.
 */
public interface ExternalOauth2TokenValidatorService {
    ExternalUserViewModel getUserInfo(String token) throws IOException, ExternalOauth2TokenException;
    String getValidationUrl();
}
