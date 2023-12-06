package com.project.supermarket_be.api.dto.request;

import lombok.Data;

@Data
public class CreateStaffRequest {
    private String name;
    private String email;
    private String password;
    private boolean gender;
    private String role = "STAFF";
    private String phone;
    private String position;
    private boolean hasWarehouse;
    private boolean hasShelf;
    private boolean hasSupply;
    private boolean hasAudit;
    private boolean hasStatistic;
}

