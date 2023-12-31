package com.project.supermarket_be.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CreateInventoryRequest {
    private Integer createByStaff;
    private String inventoryTime;
    private String inventoryCode;
    private String staffSignature;
    private String note;
    private List<ProductOnInventoryRequest> products;
}
