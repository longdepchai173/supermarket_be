package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompartmentResponse {
    private Integer compartmentId;
    private Integer tierId;
    private String compartmentCode;
    private Integer currentQuantity;
    private Integer productId;
    private String productName;
    private String categoryName;
}
