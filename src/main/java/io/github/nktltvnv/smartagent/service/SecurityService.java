package io.github.nktltvnv.smartagent.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public Optional<String> getCurrentUserId(final Principal principal) {
        if (principal instanceof JwtAuthenticationToken token) {
            return Optional.of(token.getTokenAttributes().get("userId").toString());
        }
        return Optional.empty();
    }
}
