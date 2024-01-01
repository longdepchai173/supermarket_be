package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class InventoryResponse {
    Integer inventoryId;
    String inventoryCode;
    Date inventoryTime;
    String productName;
    String productCode;
    String batchCode;
}
