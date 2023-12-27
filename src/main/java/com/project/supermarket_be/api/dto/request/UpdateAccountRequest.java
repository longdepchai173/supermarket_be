package com.project.supermarket_be.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAccountRequest {
    private String accountId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private boolean gender;
    private String position;
    private boolean hasWarehouse;
    private boolean hasShelf;
    private boolean hasSupply;
    private boolean hasAudit;
    private boolean hasStatistic;
    private boolean hasCategory;
}
