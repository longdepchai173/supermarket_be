package com.project.supermarket_be.api.dto.mapping_output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductIdCurrentQnt {
    private Integer productId;
    private Integer currentQuantity;
}
