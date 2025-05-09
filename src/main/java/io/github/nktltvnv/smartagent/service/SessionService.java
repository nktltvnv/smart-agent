package io.github.nktltvnv.smartagent.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.server.WebSession;

@Service
public class SessionService {

    public Optional<String> getAttribute(final String key, final WebSession session) {
        if (!session.isStarted()) {
            session.start();
            return Optional.empty();
        }
        return Optional.ofNullable(session.getAttribute(key));
    }

    public void setAttribute(final String key, final String attribute, final WebSession session) {
        if (!session.isStarted()) {
            session.start();
        }
        session.getAttributes().put(key, attribute);
    }
}
