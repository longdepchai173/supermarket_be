package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInventoryDto {
    Long productId;
    Long inventoryId;
    String status;
    Integer quantity;
}
