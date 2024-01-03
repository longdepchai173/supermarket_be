package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.parameter.GetAllProductParam;
import com.project.supermarket_be.api.dto.request.AddProductToShelfRequest;
import com.project.supermarket_be.api.dto.request.CreatesStockInvoice;
import com.project.supermarket_be.api.dto.request.ProductRequest;
import com.project.supermarket_be.api.dto.response.ProductIdCategoryNameDto;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.Provider;
import com.project.supermarket_be.domain.model.WarehouseInvoice;

public interface ProductService {
    Product createProduct(ProductRequest product, Provider provider, WarehouseInvoice invoice);
    ReturnResponse getProductById(String productId);
    Product getProductById(Long productId);
    ProductIdCategoryNameDto getNameAndCategoryId(Long productId);

    ReturnResponse getAllProductPaging(GetAllProductParam param);
    Integer getShelfQuantity(Long productId);

    boolean updateShelfQuantity(Integer subQnt, Long productId);
}
