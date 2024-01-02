package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class GetInventoryResponse {
    Date timeInventory;
    String tnventoryCode;
    String signatureOfClerk;
    String nameOfClerk;
    List<ProductInInventoryResponse> products;
}
