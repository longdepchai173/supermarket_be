package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CreateInventoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;

public interface InventoryService {
    ReturnResponse create(CreateInventoryRequest request);

}
