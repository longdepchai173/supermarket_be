package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.ProductOnInventoryRequest;
import com.project.supermarket_be.api.dto.response.ProductInventoryDto;
import com.project.supermarket_be.domain.model.Inventory;
import com.project.supermarket_be.domain.model.ProductInventory;
import com.project.supermarket_be.domain.repository.InventoryRepo;
import com.project.supermarket_be.domain.repository.ProductInventoryRepo;
import com.project.supermarket_be.domain.service.ProductInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductInventoryServiceImpl implements ProductInventoryService {
    private final ProductInventoryRepo repo;
    @Override
    public Boolean create(ProductInventoryDto dto) {
        return null;
    }

    @Override
    public boolean addListProduct(Inventory inventorySaved, List<ProductOnInventoryRequest> products) {

        for(ProductOnInventoryRequest request : products){
            ProductInventory productInventory = ProductInventory.builder()
                    .inventoryId(Math.toIntExact(inventorySaved.getInventoryId()))
                    .productId(request.getProductId())
                    .status(request.getStatus())
                    .quantity(request.getQuantity())
                    .deletedFlag(false)
                    .build();
            repo.save(productInventory);
        }
        return true;
    }
}
