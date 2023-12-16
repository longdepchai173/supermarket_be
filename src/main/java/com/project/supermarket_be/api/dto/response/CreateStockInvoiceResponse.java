package com.project.supermarket_be.api.dto.response;

import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.WarehouseInvoice;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateStockInvoiceResponse {
    private WarehouseInvoice warehouseInvoice;
    private List<Product> products;
}
