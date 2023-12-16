package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CreatesStockInvoice;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.WarehouseInvoice;

import java.io.IOException;

public interface WarehouseService {
    ReturnResponse createWarehouseInvoice(CreatesStockInvoice invoice) throws IOException;
    WarehouseInvoice getById(Long id);
}
