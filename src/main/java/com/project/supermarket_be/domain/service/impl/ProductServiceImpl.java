package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.mapping_output.GetAllProductByConditionDto;
import com.project.supermarket_be.api.dto.parameter.GetAllProductParam;
import com.project.supermarket_be.api.dto.request.AddProductToShelfRequest;
import com.project.supermarket_be.api.dto.request.CreatesStockInvoice;
import com.project.supermarket_be.api.dto.request.ProductRequest;
import com.project.supermarket_be.api.dto.response.ProductIdCategoryNameDto;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CannotCreateProduct;
import com.project.supermarket_be.api.exception.customerException.CategoryNotFound;
import com.project.supermarket_be.api.exception.customerException.ProductCannotFound;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.model.Product;
import com.project.supermarket_be.domain.model.Provider;
import com.project.supermarket_be.domain.model.WarehouseInvoice;
import com.project.supermarket_be.domain.repository.ProductRepo;
import com.project.supermarket_be.domain.service.CategoryService;
import com.project.supermarket_be.domain.service.CompartmentService;
import com.project.supermarket_be.domain.service.ProductService;
import com.project.supermarket_be.domain.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                    .productName(product.getProductName())
                    .category(categoryService.findById(product.getCategoryId()))
                    .productCode(product.getProductCode())
                    .batchCode(product.getBatchCode())
                    .inputPrice(BigDecimal.valueOf(product.getInputPrice()))
                    .inputQuantity(product.getInputQuantity())
                    .soldQuantity(product.getSoldQuantity())
                    .shelfQuantity(product.getShelfQuantity())
                    .shelfArrangeQuantity(product.getShelfArrangeQnt())
                    .disableDate(product.getDisableDate())
                    .expiredDate(product.getExpiredDate())
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

    @Override
    public ReturnResponse getProductById(String productId) {
        Long covProductId = Long.valueOf(productId);
        Product product = repo.findById(covProductId).orElseThrow(()->new ProductCannotFound(productId));
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(product)
                .build();
    }

    @Override
    public Product getProductById(Long productId) {
        return repo.findById(productId).orElseThrow(()->new ProductCannotFound(String.valueOf(productId)));
    }

    @Override
    public ProductIdCategoryNameDto getNameAndCategoryId(Long productId) {
        List<Object[]> results = repo.getProductName(productId);
        if(results.isEmpty()){
            throw new ProductCannotFound(String.valueOf(productId));
        }
        for(Object[] result : results){
            String product_name = (String)result[0];
            Integer category_id = (Integer)result[1];
            return ProductIdCategoryNameDto.builder()
                    .productName(product_name)
                    .categoryId(category_id)
                    .build();
        }
        return null;
    }

    @Override
    public ReturnResponse getAllProductPaging(GetAllProductParam param) {
        String search = param.getSearch();
        String from = param.getFrom();
        String to = param.getTo();
        if(from.equals("") || to.equals("")){
            from = "01-01-0001";
            to = "01-01-2100";
        }
        List<GetAllProductByConditionDto> productByCategoryName = repo.getProductsInfoByCondition(search, from, to);

        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(productByCategoryName)
                .build();
    }

    @Override
    public Integer getShelfQuantity(Long productId) {
        return repo.getShelfQuantity(productId);
    }

    @Override
    public boolean updateShelfQuantity(Integer subQnt, Long productId) {
        Product product = repo.findById(productId).orElseThrow(
                ()-> new ProductCannotFound(String.valueOf(productId)));
        product.setShelfQuantity(subQnt);
        try{
            repo.save(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
