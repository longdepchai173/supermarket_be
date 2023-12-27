package com.project.supermarket_be.api.dto.request;

import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CreatesStockInvoice {
    private Long staffId;
    private String providerCode;
    private Date receivingTime;
    private Long providerId;
    private String supplyCode;
    private String deliveryPersonName;
    private String receivingClerkName;
    private String deliverySignature;
    private String receivingSignature;
    private String note;
    private List<ProductRequest> listProducts;
}
