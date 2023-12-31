package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.ProductOnInventoryRequest;
import com.project.supermarket_be.api.dto.response.ProductInventoryDto;
import com.project.supermarket_be.domain.model.Inventory;

import java.util.List;

public interface ProductInventoryService {
    Boolean create(ProductInventoryDto dto);

    boolean addListProduct(Inventory inventorySaved, List<ProductOnInventoryRequest> products);
}
