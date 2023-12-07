package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ReturnResponse getAccountDetailById(String id);
}
