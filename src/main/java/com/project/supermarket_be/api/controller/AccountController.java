package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("{accountId}")
    public ResponseEntity<ReturnResponse> getAccountDetail (@PathVariable String accountId){
        ReturnResponse response = accountService.getAccountDetailById(accountId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @PostMapping("/create-staff-account")
    public ResponseEntity<ReturnResponse> createStaffAccount(@RequestBody CreateStaffRequest request){
        ReturnResponse response = accountService.createStaffAccount(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
