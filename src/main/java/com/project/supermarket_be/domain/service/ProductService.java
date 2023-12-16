package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CreatesStockInvoice;
import com.project.supermarket_be.api.dto.request.ProductRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.WarehouseInvoice;

public interface ProductService {
    Product createProduct(ProductRequest product, WarehouseInvoice invoice);
}
