package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ProviderDashboard {
    Integer id;
    String companyName;
    BigDecimal total;
}
