package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CreatesStockInvoice;
import com.project.supermarket_be.api.dto.request.ProductRequest;
import com.project.supermarket_be.api.dto.response.CreateStockInvoiceResponse;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CanNotUploadImage;
import com.project.supermarket_be.api.exception.customerException.CannotFoundWarehouseInvoice;
import com.project.supermarket_be.domain.model.Account;
import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.WarehouseInvoice;
import com.project.supermarket_be.domain.repository.WarehouseRepo;
import com.project.supermarket_be.domain.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final AccountService accountService;
    private final ProductService productService;
    private final UploadImgService uploadImgService;
    private final ProviderService providerService;
    private final WarehouseRepo repo;

    @Override
    public ReturnResponse createWarehouseInvoice(CreatesStockInvoice invoice) throws IOException {
        String deliverySignatureUrl;
        String receivingSignatureUrl;

        try {
            deliverySignatureUrl = uploadImgService.uploadBase64Image(invoice.getDeliverySignature());
        } catch (Exception e) {
            throw new CanNotUploadImage(invoice.getDeliverySignature());
        }
        try {
            receivingSignatureUrl = uploadImgService.uploadBase64Image(invoice.getReceivingSignature());
        } catch (Exception e) {
            throw new CanNotUploadImage(invoice.getReceivingSignature());
        }

        Account account = accountService.getAccountById(invoice.getStaffId());

        WarehouseInvoice warehouseInvoice = WarehouseInvoice.builder()
                .staff(account)
                .supplyCode(invoice.getSupplyCode())
                .receivingTime(invoice.getReceivingTime())
                .deliverySignature(deliverySignatureUrl)
                .receivingSignature(receivingSignatureUrl)
                .receivingClerkName(invoice.getReceivingClerkName())
                .note(invoice.getNote())
                .supplyCode(invoice.getSupplyCode())
                .deliveryPersonName(invoice.getDeliveryPersonName())
                .deletedFlag(false)
                .build();

        WarehouseInvoice savedInvoice = repo.save(warehouseInvoice);
        List<Product> productListResult = new ArrayList<>();
        try{
            for (ProductRequest product : invoice.getListProducts()) {
                Product temp = productService.createProduct(product, savedInvoice);
                productListResult.add(temp);
            }
        }
        catch (Exception e){
            repo.delete(savedInvoice);
        }

        CreateStockInvoiceResponse returnData = CreateStockInvoiceResponse.builder()
                .warehouseInvoice(savedInvoice)
                .products(productListResult)
                .build();

        ReturnResponse returnResponse = ReturnResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .data(returnData)
                .build();
        return returnResponse;
    }


    @Override
    public WarehouseInvoice getById(Long id) {
        return repo.findById(id).orElseThrow(()-> new CannotFoundWarehouseInvoice(String.valueOf(id)));
    }
}
