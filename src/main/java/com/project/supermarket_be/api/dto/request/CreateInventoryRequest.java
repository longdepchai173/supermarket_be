package com.project.supermarket_be.api.dto.parameter;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CreateInventoryRequest {
    private Integer createByStaff;
    private Date inventoryTime;
    private String inventoryCode;
    private String staffSignature;
    private String note;
    private List<ProductOnInventory> products;
    @Data
    @Builder
    static class ProductOnInventory {
        private Integer productId;
        private Integer quantity;
        private Integer status;
    }
}
