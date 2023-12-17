package com.project.supermarket_be.api.dto.request;

import lombok.Data;

@Data
public class CreateProviderRequest {
    private String name;
    private String contactPerson;
    private String emailContact;
    private String phoneContact;
    private String address;
    private String safetyInspection;
    private String note;
}
