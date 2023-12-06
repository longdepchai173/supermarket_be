package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.request.RequestLogin;
import com.project.supermarket_be.api.dto.response.AuthResponse;
import com.project.supermarket_be.api.dto.response.ReturnResponse;

public interface AuthService {
    AuthResponse authenticate(RequestLogin requestLogin);
    ReturnResponse createStaffAccount(CreateStaffRequest request);
}
