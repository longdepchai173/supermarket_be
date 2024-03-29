package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CreateInventoryRequest;
import com.project.supermarket_be.api.dto.request.UpdateInventoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;

public interface InventoryService {
    ReturnResponse create(CreateInventoryRequest request, String token);
    ReturnResponse getAll(String search, String from, String to);

    ReturnResponse getById(Integer inventoryId);

    ReturnResponse deleteById(Integer inventoryId);

    ReturnResponse update(UpdateInventoryRequest request);
}
