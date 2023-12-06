package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.RequestLogin;
import com.project.supermarket_be.api.exception.customerException.PasswordErrorException;
import com.project.supermarket_be.api.exception.customerException.UserNotFoundException;
import com.project.supermarket_be.api.response.AuthResponse;
import com.project.supermarket_be.domain.model.Account;
import com.project.supermarket_be.domain.repository.AccountRepo;
import com.project.supermarket_be.domain.service.AuthService;
import com.project.supermarket_be.domain.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {
    private final AccountRepo accountRepo;
    private final JwtService jwtService;
    @Override
    public AuthResponse authenticate(RequestLogin requestLogin) {
        Account account = accountRepo.findByEmail(requestLogin.getEmail());
        if(account == null){
            throw new UserNotFoundException(requestLogin.getEmail());
        }
        if(requestLogin.getPassword().equals(account.getPassword())) {
            String accessToken = jwtService.generateAccessToken(account);
            return AuthResponse
                    .builder()
                    .statusCode(HttpStatus.OK)
                    .data(account)
                    .accessToken(accessToken).build();
        }else
            throw new PasswordErrorException(requestLogin.getEmail());
    }
}
