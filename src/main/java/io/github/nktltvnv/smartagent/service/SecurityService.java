package io.github.nktltvnv.smartagent.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import io.github.nktltvnv.smartagent.util.constant.SecurityConstant;

@Service
public class SecurityService {

    public Optional<String> getCurrentUserId(final Principal principal) {
        return principal instanceof JwtAuthenticationToken jwt
                ? Optional.of(jwt.getTokenAttributes()
                        .get(SecurityConstant.JWT_USER_ID_KEY)
                        .toString())
                : Optional.empty();
    }
}
