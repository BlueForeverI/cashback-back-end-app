package com.cashback.api.util;

import com.cashback.api.config.security.SecurityConfiguration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by George on 9.5.2017 г..
 */
@Service
public class GeneralHelpers {

    private final transient ShaPasswordEncoder passwordEncoder =
            new ShaPasswordEncoder(SecurityConfiguration.SHA_PASSWORD_STRENGTH);

    public String encodePassword(String pass) {
        return passwordEncoder.encodePassword(pass, "");
    }
}
