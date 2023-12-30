package com.project.supermarket_be.api.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ProductRequest {
    private Long categoryId;
    private String productName;
    private Long invoiceId;
    private Long supplyId;
    private String productCode;
    private String batchCode;
    private int inputPrice;
    private int inputQuantity;
    private int soldQuantity;
    private int shelfQuantity;
    private int shelfArrangeQnt;
    private boolean isDisable;
    private Date disableDate;
    private Date expiredDate;
    private Date manufactureDate;
}
