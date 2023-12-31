package com.project.supermarket_be.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOnInventoryRequest {
    private Integer productId;
    private Integer inventoryId;
    private Integer quantity;
    private String status;
}
