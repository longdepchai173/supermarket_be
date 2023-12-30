package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CreateShelfRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;

public interface ShelfService {
    ReturnResponse getAll();
    ReturnResponse create(CreateShelfRequest request);
    ReturnResponse update(Long id);
    ReturnResponse delete(Long id);
}
