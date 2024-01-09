package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Builder
@Data
public class IncomeDashboard {
    Integer month;
    BigDecimal money;
}
