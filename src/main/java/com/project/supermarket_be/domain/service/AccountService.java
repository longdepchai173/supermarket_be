package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.parameter.GetAllAccountParam;
import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.request.UpdateAccountRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Account;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ReturnResponse getAccountDetailById(String id);
    ReturnResponse createStaffAccount(CreateStaffRequest request);
    ReturnResponse getAllAccountPaging(GetAllAccountParam param);
    ReturnResponse getAccountByToken(String token);
    ReturnResponse blockAccountById(String id);
    Account getAccountById(Long id);
    ReturnResponse update(UpdateAccountRequest request);
}
