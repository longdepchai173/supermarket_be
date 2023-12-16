package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.request.CreatesStockInvoice;
import com.project.supermarket_be.api.dto.response.CreateStockInvoiceResponse;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks-invoice")
public class StockInvoiceController {
    private final WarehouseService service;
    @PostMapping(value =  "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnResponse> createStockInvoice(@RequestBody CreatesStockInvoice stockInvoice) throws IOException {

        ReturnResponse response = service.createWarehouseInvoice(stockInvoice);
        return ResponseEntity.status(HttpStatus.OK).body(ReturnResponse.builder()
                .statusCode(HttpStatus.OK).data(response).build());
    }
}
