package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDashboard {
    String type;
    Long value;
}
