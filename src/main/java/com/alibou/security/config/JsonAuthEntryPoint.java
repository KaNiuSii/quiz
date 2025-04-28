package com.alibou.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JsonAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest  request,
                         HttpServletResponse response,
                         AuthenticationException ex) throws IOException, java.io.IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("""
            { "status": 401,
              "error":  "UNAUTHORIZED",
              "message": "%s",
              "path": "%s" }
            """.formatted(ex.getMessage(), request.getRequestURI()));
    }
}
