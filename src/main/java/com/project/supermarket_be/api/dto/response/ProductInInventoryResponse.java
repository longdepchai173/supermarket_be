package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductInInventoryResponse {
    Integer productId;
    String categoryName;
    String productName;
    Integer quantity;
    String status;
    Date expiredDate;
    String productCode;
}

