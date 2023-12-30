package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.parameter.GetAllAccountParam;
import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.request.UpdateAccountRequest;
import com.project.supermarket_be.api.dto.response.GetAccountResponse;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.*;
import com.project.supermarket_be.domain.enums.FindAccountBy;
import com.project.supermarket_be.domain.model.Account;
import com.project.supermarket_be.domain.repository.AccountRepo;
import com.project.supermarket_be.domain.service.AccountService;
import com.project.supermarket_be.domain.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final JwtService jwtService;

    @Override
    public ReturnResponse getAccountDetailById(String accountId) {
        long convId = Long.parseLong(accountId);
        Account account = accountRepo.findById(convId).orElseThrow(() -> new UserIDNotFoundException(accountId));
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK).data(account)
                .build();
    }

    @Override
    public ReturnResponse createStaffAccount(CreateStaffRequest request) {
        Account account = accountRepo.findByEmail(request.getEmail());
        if (account == null) {
            Account newStaffAccount = Account.fromCreateStaffRequest(request);
            Account createdAccount = accountRepo.save(newStaffAccount);
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.CREATED)
                    .data(createdAccount)
                    .build();
        }
        throw new CannotCreateUser(request.getEmail());
    }

    @Override
    public ReturnResponse getAllAccountPaging(GetAllAccountParam param) {
        List<Account> result = new ArrayList<>();
        if(param.getSearch().isEmpty()){
            result = accountRepo.findAll();
        }else
            result = accountRepo.getAllAccountByCondition(param.getSearch(), param.getStatus());

        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK).data(result)
                .build();
    }

    @Override
    public ReturnResponse getAccountByToken(String token) {
        String accessToken = token.substring(7);
        String email = jwtService.extractUsername(accessToken);
        Account account = accountRepo.findByEmail(email);
        return ReturnResponse.builder()
                .data(account)
                .statusCode(HttpStatus.OK)
                .build();
    }

    @Override
    public ReturnResponse blockAccountById(String id) {
        long convId = Long.parseLong(id);
        Account account = accountRepo.findById(convId).orElseThrow(() -> new UserIDNotFoundException(id));

        // Assuming you have a status constant for blocked accounts, e.g., AccountStatus.BLOCKED
        account.setStatus(0);

        Account blockedAccount = accountRepo.save(account);

        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(blockedAccount)
                .build();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepo.findById(id).orElseThrow(()->new UserIDNotFoundException(String.valueOf(id)));
    }

    @Override
    public ReturnResponse update(UpdateAccountRequest request) {
        Long covAccountId = Long.valueOf(request.getAccountId());
        Account account = accountRepo.findById(covAccountId).orElseThrow(()->new UserIDNotFoundException(request.getAccountId()));
        account.setName(request.getName());
//        account.setEmail(request.getEmail());
        if (request.getPassword().equals("")) {
            System.out.println("");
        } else {
            account.setPassword(request.getPassword());
        }
        account.setGender(request.isGender());
        account.setPhone(request.getPhone());
        account.setPosition(request.getPosition());
        account.setHasWarehouse(request.isHasWarehouse());
        account.setHasShelf(request.isHasShelf());
        account.setHasSupply(request.isHasSupply());
        account.setHasAudit(request.isHasAudit());
        account.setHasCategory(request.isHasCategory());

        accountRepo.save(account);
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(account)
                .build();
    }

}
