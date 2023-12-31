package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.ProductOnInventoryRequest;
import com.project.supermarket_be.api.dto.request.ProductRequest;
import com.project.supermarket_be.api.dto.response.ProductInventoryDto;
import com.project.supermarket_be.domain.model.Inventory;
import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.ProductInventory;
import com.project.supermarket_be.domain.repository.ProductInventoryRepo;
import com.project.supermarket_be.domain.service.ProductInventoryService;
import com.project.supermarket_be.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductInventoryServiceImpl implements ProductInventoryService {
    private final ProductInventoryRepo repo;
    private final ProductService productService;
    @Override
    public Boolean create(ProductInventoryDto dto) {
        return null;
    }

    @Override
    public boolean addListProduct(Inventory inventorySaved, List<ProductOnInventoryRequest> products) {
        List<ProductInventory> productInventories = new ArrayList<>();
        for(ProductOnInventoryRequest request : products){
            Product product = productService.getProductById(Long.valueOf(request.getProductId()));
            ProductInventory productInventory = ProductInventory.builder()
                    .inventory(inventorySaved)
                    .product(product)
                    .status(request.getStatus())
                    .quantity(request.getQuantity())
                    .deletedFlag(false)
                    .build();
            productInventories.add(productInventory);
//            repo.storeData(Math.toIntExact(product.getId()), Math.toIntExact(inventorySaved.getInventoryId()), request.getStatus(), request.getQuantity());
        }
        repo.saveAll(productInventories);
        return true;
    }
}
