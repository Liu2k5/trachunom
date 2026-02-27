package com.liu.trachunom.endpoint;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
public class AuthEndpoint {

    public record AuthInfo(boolean loggedIn, String username, List<String> roles) {}

    public AuthInfo getAuthInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()
                || "anonymousUser".equals(auth.getPrincipal())) {
            return new AuthInfo(false, null, List.of());
        }
        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return new AuthInfo(true, auth.getName(), roles);
    }
}
