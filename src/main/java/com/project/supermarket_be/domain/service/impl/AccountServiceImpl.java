package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CannotCreateUser;
import com.project.supermarket_be.api.exception.customerException.UserIDNotFoundException;
import com.project.supermarket_be.api.exception.customerException.UserNotFoundException;
import com.project.supermarket_be.domain.model.Account;
import com.project.supermarket_be.domain.repository.AccountRepo;
import com.project.supermarket_be.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;

    @Override
    public ReturnResponse getAccountDetailById(String accountId) {
        long convId = Long.parseLong(accountId);
        Account account = accountRepo.findById(convId).orElseThrow(()-> new UserIDNotFoundException(accountId));
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK).data(account)
                .build();
    }

    @Override
    public ReturnResponse createStaffAccount(CreateStaffRequest request) {
        Account account = accountRepo.findByEmail(request.getEmail());
        if(account == null){
            Account newStaffAccount = Account.fromCreateStaffRequest(request);
            Account createdAccount = accountRepo.save(newStaffAccount);
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.CREATED)
                    .data(createdAccount)
                    .build();
        }
        throw new CannotCreateUser(request.getEmail());
    }
}
