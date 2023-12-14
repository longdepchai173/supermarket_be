package com.project.supermarket_be.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.supermarket_be.api.exception.customerException.NotHavePermissionException;
import com.project.supermarket_be.api.exception.customerException.TokenIsEmptyException;
import com.project.supermarket_be.api.exception.customerException.UserIDNotFoundException;
import com.project.supermarket_be.api.exception.customerException.UserNotFoundException;
import com.project.supermarket_be.domain.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenAdminValidationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = "";
        String uri = request.getRequestURI();
        if (uri.contains("auth"))
            filterChain.doFilter(request, response);
        else {
            token = request.getHeader("Authorization");
            if (token == null)
                throw new UserIDNotFoundException("999999999");

            boolean isValidToken = isValidToken(token);
            String accessToken = token.substring(7);
            String role = jwtService.exactRole(accessToken);

            if (isValidToken && uri.contains("accounts")) {
                if (role.equals("ADMIN"))
                    filterChain.doFilter(request, response);
                else
                    customizeForbiddenResponse(response, "You haven't permission");
            } else if (isValidToken) {
                filterChain.doFilter(request, response);
            } else {
                // If the token is not valid, you can return an unauthorized response
                customizeUnauthorizedResponse(response, "Token invalid");
            }
        }
    }

    private boolean isValidToken(String token) {
        // Implement your token validation logic here
        // Return true if the token is valid, false otherwise
        // You may want to check the token against a database or some other validation mechanism
        return token != null && token.startsWith("Bearer ");
    }

    private void customizeForbiddenResponse(HttpServletResponse response, String errorMessage) throws IOException {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("statusCode", String.valueOf(HttpStatus.FORBIDDEN));
        errorResponse.put("message", errorMessage);

        sendJsonResponse(response, HttpStatus.FORBIDDEN, errorResponse);
    }

    private void customizeUnauthorizedResponse(HttpServletResponse response, String errorMessage) throws IOException {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("statusCode", String.valueOf(HttpStatus.UNAUTHORIZED));
        errorResponse.put("message", errorMessage);

        sendJsonResponse(response, HttpStatus.UNAUTHORIZED, errorResponse);
    }

    private void sendJsonResponse(HttpServletResponse response, HttpStatus status, Map<String, ?> body) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), body);
    }
}
