package com.alibou.security.config;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest  request,
                       HttpServletResponse response,
                       org.springframework.security.access.AccessDeniedException ex) throws IOException, java.io.IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);    // 403
        response.setContentType("application/json");
        response.getWriter().write("""
            { "status": 403,
              "error":  "FORBIDDEN",
              "message": "%s",
              "path": "%s" }
            """.formatted(ex.getMessage(), request.getRequestURI()));
    }

}
