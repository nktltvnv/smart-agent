package io.github.nktltvnv.smartagent.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import io.github.nktltvnv.smartagent.util.constant.SecurityConstant;

@Service
public class SecurityService {

    public Optional<String> getCurrentUserId(final Principal principal) {
        if (principal instanceof JwtAuthenticationToken token) {
            return Optional.of(token.getTokenAttributes()
                    .get(SecurityConstant.JWT_USER_ID_KEY)
                    .toString());
        }
        return Optional.empty();
    }
}
