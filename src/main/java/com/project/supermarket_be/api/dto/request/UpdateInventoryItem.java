package com.project.supermarket_be.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateInventoryItem {
    Integer productInventoryId;
    Integer quantity;
    String status;
}
