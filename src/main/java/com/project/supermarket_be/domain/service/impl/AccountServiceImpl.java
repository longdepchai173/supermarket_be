package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.parameter.GetAllAccountParam;
import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
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
        PageRequest pageRequest = PageRequest.of(param.getPageNumber(), param.getLimit());
        Page<Account> page;
        String search = param.getSearch();
        Integer status = param.getStatus();
        String position = param.getPosition();

        if(search.equals("all")){
           if(status == -1){
               if(position.equals("all")){
                   page = accountRepo.findAll(pageRequest);
               }else
                   page = accountRepo.findByPositionContaining(position, pageRequest);
           }else{
               if(position.equals("all")){
                    page = accountRepo.findByStatus(status, pageRequest);
               }else
                   page = accountRepo.findByPositionContainingAndStatus(position, status, pageRequest);
           }
        }else { // name have character
            if(status == -1){ // status not have value
                if(position.equals("all")){
                    page = accountRepo.findByNameContaining(search, pageRequest);
                }else {
                    page = accountRepo.findByPositionContainingAndNameContaining(position, search, pageRequest);
                }
            }
            else {
                if(position.equals("all")){
                    page = accountRepo.findByNameContainingAndStatus(search, status, pageRequest);
                }else{
                    page = accountRepo.findByNameContainingAndPositionContainingAndStatus(
                            search, position, status, pageRequest);
                }
            }
        }

        List<Account> accountList = page.getContent();

        GetAccountResponse result = GetAccountResponse.builder()
                .currentPage(param.getPageNumber())
                .totalPage(page.getTotalPages())
                .accounts(accountList)
                .build();

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

}
