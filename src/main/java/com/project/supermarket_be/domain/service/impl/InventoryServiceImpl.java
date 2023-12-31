package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CreateInventoryRequest;
import com.project.supermarket_be.api.dto.request.ProductOnInventoryRequest;
import com.project.supermarket_be.api.dto.response.ProductInventoryDto;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CanNotUploadImage;
import com.project.supermarket_be.domain.model.Account;
import com.project.supermarket_be.domain.model.Inventory;
import com.project.supermarket_be.domain.model.ProductInventory;
import com.project.supermarket_be.domain.repository.InventoryRepo;
import com.project.supermarket_be.domain.repository.ProductInventoryRepo;
import com.project.supermarket_be.domain.service.*;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepo repo;
    private final AccountService accountService;
    private final UploadImgService uploadImgService;
    private final ProductInventoryRepo productInventoryRepo;
    private final ProductInventoryService productInventoryService;

    @Override
    public ReturnResponse create(CreateInventoryRequest request) {
        String staffSignatureUrl = null;
        try {
            staffSignatureUrl = uploadImgService.uploadBase64Image(request.getStaffSignature());
        } catch (Exception e) {
            throw new CanNotUploadImage(request.getStaffSignature());
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date covInventoryTime = new Date();

        try {
            Date date = inputFormat.parse(request.getInventoryTime());
            covInventoryTime = outputFormat.parse(request.getInventoryTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        Account account = accountService.getAccountById(Long.valueOf(request.getCreateByStaff()));
        Inventory inventory = Inventory.builder()
                .createByStaff(account)
                .inventoryCode(request.getInventoryCode())
                .note(request.getNote())
                .inventoryTime(covInventoryTime)
                .staffSignature(staffSignatureUrl)
                .deletedFlag(false)
                .build();
        Inventory inventorySaved = repo.save(inventory);

        boolean check = insetTo(inventorySaved, request.getProducts());

        return check ? ReturnResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .data("create successfully")
                .build() : ReturnResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .data("loi roi huhu")
                .build();
    }

    private boolean insetTo(Inventory inventorySaved, List<ProductOnInventoryRequest> products) {
        Integer inventoryId = Math.toIntExact(inventorySaved.getInventoryId());
        List<ProductInventory> result = new ArrayList<>();
        try {
            for(int i = 0; i < products.size(); i++){
               Integer productId = products.get(i).getProductId();
               Integer quantity = products.get(i).getQuantity();
               String status = products.get(i).getStatus();

                ProductInventory productInventory = ProductInventory.builder()
                        .productId(productId)
                        .inventoryId(inventoryId)
                        .quantity(quantity)
                        .status(status)
                        .build();
                result.add(productInventory);

//               repo.storeDataIn(productId, inventoryId, status, quantity);
            }
            productInventoryRepo.saveAll(result);
            return true;
        } catch (Exception e) {
            repo.delete(inventorySaved);
            return false;
        }
    }
}
