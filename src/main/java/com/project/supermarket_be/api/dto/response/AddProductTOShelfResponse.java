package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddProductTOShelfResponse {
    Integer compartmentId;
    Integer tierId;
    String compartmentCode;
    Integer currentQuantity;
    Integer productId;
    Integer productName;
    String categoryName;
}
