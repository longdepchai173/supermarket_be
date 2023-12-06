package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.RequestLogin;
import com.project.supermarket_be.api.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(RequestLogin requestLogin);
}
