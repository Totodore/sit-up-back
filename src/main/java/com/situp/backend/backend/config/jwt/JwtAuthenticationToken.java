package com.situp.backend.backend.config.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JwtAuthenticationToken implements Authentication {

    private TokenPayload tokenPayload;

    private final List<GrantedAuthority> authorities;
    private final Map<String, Claim> claims;
    private boolean isAuthenticated;

    JwtAuthenticationToken(DecodedJWT jwt) {
        List<GrantedAuthority> tmp = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.tokenPayload = mapper.readValue(jwt.getClaim(JwtTokenUtil.CLAIM_PAYLOAD).asString(),
                    TokenPayload.class);
            if (tokenPayload.isAdmin()) {
                tmp.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                tmp.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.authorities = Collections.unmodifiableList(tmp);
        this.claims = jwt.getClaims();
        this.isAuthenticated = false;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return claims;
    }

    @Override
    public Object getPrincipal() {
        return tokenPayload;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.isAuthenticated = b;
    }

    @Override
    public String getName() {
        return null;
    }
}