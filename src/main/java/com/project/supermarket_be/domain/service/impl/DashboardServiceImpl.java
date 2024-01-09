package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.response.*;
import com.project.supermarket_be.domain.repository.InventoryRepo;
import com.project.supermarket_be.domain.repository.ProductRepo;
import com.project.supermarket_be.domain.repository.ProviderRepo;
import com.project.supermarket_be.domain.repository.WarehouseRepo;
import com.project.supermarket_be.domain.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final ProductRepo productRepo;
    private final ProviderRepo providerRepo;
    private final WarehouseRepo warehouseRepo;
    @Override
    public ReturnResponse get(Integer month, Integer year) {
        try{
            List<Object[]> products = productRepo.getDashboard();
            List<Object[]> providers = providerRepo.getDashboard(month, year);
            List<Object[]> warehouses = warehouseRepo.getDashboard(year);

            List<ProductDashboard> productDashboards = new ArrayList<>();
            if(!products.isEmpty()) {
                for (Object[] product : products) {
                    ProductDashboard productDashboard = ProductDashboard.builder()
                            .type("on stock")
                            .value((Long) product[0])
                            .build();
                    productDashboards.add(productDashboard);
                    productDashboard = ProductDashboard.builder()
                            .type("sold")
                            .value((Long) product[1])
                            .build();
                    productDashboards.add(productDashboard);
                    productDashboard = ProductDashboard.builder()
                            .type("in shelf")
                            .value((Long) product[2])
                            .build();
                    productDashboards.add(productDashboard);
                }
            }

            List<ProviderDashboard> providerDashboards = new ArrayList<>();
            if(!providers.isEmpty()){
                providerDashboards = providers.stream().map(result -> ProviderDashboard.builder()
                        .id((Integer) result[0])
                        .companyName((String) result[1])
                        .total((BigDecimal) result[2])
                        .build()).toList();
            }

            List<IncomeDashboard> incomeDashboards = new ArrayList<>();
            if(!warehouses.isEmpty()){
                incomeDashboards = warehouses.stream().map(result -> IncomeDashboard.builder()
                        .month((Integer) result[0])
                        .money((BigDecimal) result[1])
                        .build()
                ).toList();
            }
            DashboardResponse dashboardResponse = new DashboardResponse(productDashboards, providerDashboards, incomeDashboards);

            return ReturnResponse.builder()
                    .statusCode(HttpStatus.OK)
                    .data(dashboardResponse)
                    .build();

        }catch (Exception e){
            return  ReturnResponse.builder()
                    .data(e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }




    }
}
