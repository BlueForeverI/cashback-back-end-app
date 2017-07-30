package com.cashback.api.services;

import com.cashback.api.config.AppConfig;
import com.cashback.api.exceptions.ExternalOauth2TokenException;
import com.cashback.api.services.interfaces.ExternalOauth2TokenValidatorService;
import com.cashback.api.viewmodels.ExternalUserViewModel;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public class FacebookTokenValidatorService implements ExternalOauth2TokenValidatorService {

    @Autowired
    private AppConfig appConfig;

    @Override
    public ExternalUserViewModel getUserInfo(String token)
            throws IOException, ExternalOauth2TokenException {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(buildUserInfoUrl(token));
            request.addHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(request);

            Gson gson = new Gson();
            Map<String, String> parsed = gson.fromJson(
                    EntityUtils.toString(response.getEntity(), "UTF-8"), Map.class);

            httpClient.close();

            if(parsed.get("id") == null) {
                throw new ExternalOauth2TokenException();
            }

            ExternalUserViewModel user = new ExternalUserViewModel() {{
                setId(parsed.get("id"));
                setFirstName(parsed.get("first_name"));
                setLastName(parsed.get("last_name"));
            }};

            String profilePicUrl = getProfilePicture(user.getId(), token);
            user.setProfilePictureUrl(profilePicUrl);

            return user;
        } catch (Exception ex) {
            throw new ExternalOauth2TokenException();
        }
    }

    @Override
    public String getValidationUrl() {
        return "https://graph.facebook.com/v2.8";
    }

    private String buildUserInfoUrl(String accessToken)
            throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String proof = getAppSecretProof(accessToken, appConfig.getFacebookAppSecret());
        return String.format(
                "%s/me?access_token=%s&appsecret_proof=%s&type=get&fields=first_name,last_name",
                getValidationUrl(), accessToken, proof);
    }

    private String getAppSecretProof(String accessToken, String facebookAppSecret)
            throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        final Charset asciiCs = Charset.forName("US-ASCII");
        final Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        final SecretKeySpec secret_key = new SecretKeySpec(
                asciiCs.encode(facebookAppSecret).array(), "HmacSHA256");
        sha256Hmac.init(secret_key);

        return Hex.encodeHexString(sha256Hmac.doFinal(
                accessToken.getBytes("UTF-8")));
    }

    private String getProfilePicture(String id, String accessToken)
            throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String proof = getAppSecretProof(accessToken, appConfig.getFacebookAppSecret());
        String profilePicRequestUrl = String.format(
                "%s/%s/picture?access_token=%s&appsecret_proof=%s&type=large",
                getValidationUrl(), id, accessToken, proof);

        HttpClientContext context = HttpClientContext.create();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(profilePicRequestUrl);
        HttpResponse response = httpClient.execute(request, context);

        httpClient.close();

        return context.getRedirectLocations().get(0).toString();
    }
}
