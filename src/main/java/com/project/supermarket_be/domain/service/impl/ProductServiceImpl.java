package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CreatesStockInvoice;
import com.project.supermarket_be.api.dto.request.ProductRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CannotCreateProduct;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.Provider;
import com.project.supermarket_be.domain.model.WarehouseInvoice;
import com.project.supermarket_be.domain.repository.ProductRepo;
import com.project.supermarket_be.domain.service.CategoryService;
import com.project.supermarket_be.domain.service.ProductService;
import com.project.supermarket_be.domain.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;
    private final CategoryService categoryService;
    @Override
    public Product createProduct(ProductRequest product, Provider provider, WarehouseInvoice invoice) {

        Product productMapper;
        Product productSaved;
        try{
            productMapper = Product.builder()
                    .invoice(invoice)
                    .provider(provider)
                    .category(categoryService.findById(product.getCategoryId()))
                    .productCode(product.getProductCode())
                    .batchCode(product.getBatchCode())
                    .inputPrice(BigDecimal.valueOf(product.getInputPrice()))
                    .inputQuantity(product.getInputQuantity())
                    .soldQuantity(product.getSoldQuantity())
                    .shelfQuantity(product.getShelfQuantity())
                    .shelfArrangeQuantity(product.getShelfArrangeQnt())
                    .disableDate(product.getDisableDate())
                    .expiredDate(null)
                    .isDisabled(false)
                    .manufactureDate(product.getManufactureDate())
                    .deletedFlag(false)
                    .build();
            productSaved = repo.save(productMapper);
        }catch (Exception e){
            throw new CannotCreateProduct(product.getProductCode());
        }
        return productSaved;
    }

}
