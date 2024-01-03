package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.AddProductToShelfRequest;
import com.project.supermarket_be.api.dto.request.CreateShelfRequest;
import com.project.supermarket_be.api.dto.request.ShelfRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;

public interface ShelfService {
    ReturnResponse getAll();
    ReturnResponse create(CreateShelfRequest request);
    ReturnResponse update(ShelfRequest request);
    ReturnResponse delete(Long id);

    ReturnResponse addProduct(AddProductToShelfRequest request);
}
