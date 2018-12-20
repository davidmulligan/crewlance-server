package com.crewlance.server.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TokenAuthentication extends AbstractAuthenticationToken {

    private final DecodedJWT decodedJWT;
    private boolean invalidated;

    public TokenAuthentication(DecodedJWT decodedJWT) {
        super(readAuthorities(decodedJWT));
        this.decodedJWT = decodedJWT;
    }

    @Override
    public String getCredentials() {
        return decodedJWT.getToken();
    }

    @Override
    public Object getPrincipal() {
        return decodedJWT.getSubject();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Create a new Authentication object to authenticate.");
        }
        invalidated = true;
    }

    @Override
    public boolean isAuthenticated() {
        return !invalidated && !hasExpired();
    }

    private boolean hasExpired() {
        return decodedJWT.getExpiresAt().before(new Date());
    }

    private static Collection<? extends GrantedAuthority> readAuthorities(DecodedJWT decodedJWT) {
        Claim rolesClaim = decodedJWT.getClaim("https://access.control/roles");
        if (rolesClaim.isNull()) {
            return Collections.emptyList();
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] scopes = rolesClaim.asArray(String.class);
        for (String scope : scopes) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(scope);
            if (!authorities.contains(authority)) {
                authorities.add(authority);
            }
        }
        return authorities;
    }
}
