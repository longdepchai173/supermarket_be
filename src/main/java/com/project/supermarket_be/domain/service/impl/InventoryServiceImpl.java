package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.*;
import com.project.supermarket_be.api.dto.response.*;
import com.project.supermarket_be.api.exception.customerException.CanNotUploadImage;
import com.project.supermarket_be.api.exception.customerException.ProductCannotFound;
import com.project.supermarket_be.domain.model.Account;
import com.project.supermarket_be.domain.model.Inventory;
import com.project.supermarket_be.domain.model.ProductInventory;
import com.project.supermarket_be.domain.repository.InventoryRepo;
import com.project.supermarket_be.domain.repository.ProductInventoryRepo;
import com.project.supermarket_be.domain.service.*;
import com.project.supermarket_be.api.exception.customerException.InventoryCannotFound;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepo repo;
    private final AccountService accountService;
    private final UploadImgService uploadImgService;
    private final ProductInventoryRepo productInventoryRepo;
    private final ProductInventoryService productInventoryService;
    private final JwtService jwtService;

    @Override
    public ReturnResponse create(CreateInventoryRequest request, String token) {
        String staffSignatureUrl = null;
        try {
            staffSignatureUrl = uploadImgService.uploadBase64Image(request.getStaffSignature());
        } catch (Exception e) {
            throw new CanNotUploadImage(request.getStaffSignature());
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date covInventoryTime = new Date();

        try {
            Date date = inputFormat.parse(request.getInventoryTime());
            covInventoryTime = inputFormat.parse(request.getInventoryTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        String accessToken = token.substring(7);
        String email = jwtService.extractUsername(accessToken);
        Account account = accountService.getAccountByEmail(email);
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
            for (int i = 0; i < products.size(); i++) {
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

    public ReturnResponse getAll(String search, String from, String to) {


        List<Object[]> resultQuery = repo.getAll(search, from, to);
        List<InventoryResponse> responses = resultQuery.stream()
                .map(result -> InventoryResponse.builder()
                        .inventoryId((Integer) result[0])
                        .inventoryCode((String) result[1])
                        .inventoryTime((Date) result[2])
//                        .productName((String) result[3])
//                        .productCode((String) result[4])
//                        .batchCode((String) result[5])
                        .staffName((String) result[3])
                        .build()).collect(Collectors.toList());
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(responses)
                .build();
    }

    @Override
    public ReturnResponse getById(Integer inventoryId) {
        Inventory inventory = repo.findById(Long.valueOf(inventoryId)).orElseThrow(()->new InventoryCannotFound(String.valueOf(inventoryId)));
        if(inventory.isDeletedFlag()){
            throw  new InventoryCannotFound(String.valueOf(inventoryId));
        }
        List<ProductInInventoryResponse> products = productInventoryService.getProductsById(inventory.getInventoryId());
        GetInventoryResponse response = GetInventoryResponse.builder()
                .timeInventory(inventory.getInventoryTime())
                .inventoryCode(inventory.getInventoryCode())
                .signatureOfClerk(inventory.getStaffSignature())
                .nameOfClerk(inventory.getCreateByStaff().getName())
                .note(inventory.getNote())
                .products(products)
                .build();
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(response)
                .build();
    }

    @Override
    public ReturnResponse deleteById(Integer inventoryId) {
        Inventory inventory = repo.findById(Long.valueOf(inventoryId)).orElseThrow(()->new InventoryCannotFound(String.valueOf(inventoryId)));
        if(inventory.isDeletedFlag()){
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .data("Inventory with id " + inventoryId + " already deleted")
                    .build();
        }
        inventory.setDeletedFlag(true);
        repo.save(inventory);
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data("Delete inventory with " + inventoryId + " successfully")
                .build();
    }

    @Override
    public ReturnResponse update(UpdateInventoryRequest request) {
        List<ProductInventory> productInventories = new ArrayList<>();
        for(UpdateInventoryItem product : request.getProducts()){
            ProductInventory productInventory = productInventoryRepo.findById(Long.valueOf(product.getProductInventoryId())).orElseThrow(RuntimeException::new);
            productInventory.setQuantity(product.getQuantity());
            productInventory.setStatus(product.getStatus());
            ProductInventory saved = productInventoryRepo.save(productInventory);
            productInventories.add(saved);
        }

        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(productInventories)
                .build();
    }
}
