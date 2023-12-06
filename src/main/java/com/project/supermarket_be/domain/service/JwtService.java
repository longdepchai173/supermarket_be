package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.domain.model.Account;

public interface JwtService {

    String generateAccessToken(Account userDetails);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
    boolean isTokenValid(String token, Account userDetails);
}
