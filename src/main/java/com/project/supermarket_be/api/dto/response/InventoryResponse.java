package com.project.supermarket_be.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class InventoryResponse {
    Integer inventoryId;
    String inventoryCode;
    Date inventoryTime;
    @JsonIgnore
    String productName;
    @JsonIgnore
    String productCode;
    @JsonIgnore
    String batchCode;
    String staffName;
}
