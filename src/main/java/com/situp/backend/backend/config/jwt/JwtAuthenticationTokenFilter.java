package com.situp.backend.backend.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    final private static Logger LOG = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token == null && request.getQueryString() != null && request.getQueryString().contains("token=")) {
            token = URLDecoder.decode(request.getQueryString().split("token=")[1].split("&")[0], StandardCharsets.UTF_8);
            LOG.info(token);
        }
        if (token != null) {
            DecodedJWT jwt;
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
                try {
                    LOG.debug("decoding token");
                    jwt = jwtTokenUtil.decodeToken(token);
                } catch (JWTVerificationException e) {
                    LOG.debug("token expired, refresh the token");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication schema not found");
                return;
            }

            if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt);
                authentication.setAuthenticated(true);

                response.setHeader("Authorization", "Bearer " + token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}