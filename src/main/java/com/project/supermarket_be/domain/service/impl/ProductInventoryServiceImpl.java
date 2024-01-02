package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.ProductOnInventoryRequest;
import com.project.supermarket_be.api.dto.response.ProductInInventoryResponse;
import com.project.supermarket_be.api.dto.response.ProductInventoryDto;
import com.project.supermarket_be.api.exception.customerException.UserIDNotFoundException;
import com.project.supermarket_be.domain.model.Inventory;
import com.project.supermarket_be.domain.model.ProductInventory;
import com.project.supermarket_be.domain.repository.InventoryRepo;
import com.project.supermarket_be.domain.repository.ProductInventoryRepo;
import com.project.supermarket_be.domain.service.ProductInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductInInventoryResponse> getProductsById(Long inventoryId) {
        Integer covInt = Math.toIntExact(inventoryId);
        List<Object[]> results = new ArrayList<>();
        try{
            results = repo.getProductInInventory(covInt);
            return results.stream().map(result -> ProductInInventoryResponse.builder()
                    .productId((Integer) result[0])
                    .categoryName(result[1].toString())
                    .productName(result[2].toString())
                    .quantity((Integer) result[3])
                    .status(result[4].toString())
                    .expiredDate((Date) result[5])
                    .productCode(String.valueOf(result[6]))
                    .build()).collect(Collectors.toList());
        }
        catch (Exception e){
            throw new UserIDNotFoundException(e.getMessage());
        }

    }
}
