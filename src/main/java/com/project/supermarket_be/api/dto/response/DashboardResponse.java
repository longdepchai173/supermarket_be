package com.project.supermarket_be.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DashboardResponse {
    private List<ProductDashboard> inventory;
    private List<ProviderDashboard> suppliers;
    private List<IncomeDashboard> income;
    private Long countAccount;
    private Long countInventory;
    private Long countSupplier;
}
