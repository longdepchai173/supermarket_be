package com.project.supermarket_be.domain.model;


import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private WarehouseInvoice invoice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "batch_code")
    private String batchCode;

    @Column(name = "input_price")
    private BigDecimal inputPrice;

    @Column(name = "input_quantity")
    private Integer inputQuantity;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    @Column(name = "shelf_arrange_qnt")
    private Integer shelfArrangeQuantity;

    @Column(name = "shelf_qnt")
    private Integer shelfQuantity;

    @Column(name = "is_disable")
    private boolean isDisabled;

    @Column(name = "disable_date")
    private Date disableDate;

    @Column(name = "expired_date")
    private Date expiredDate;

    @Column(name = "manufacture_date")
    private Date manufactureDate;

    @Column(name = "deleted_flag")
    @JsonIgnore
    private boolean deletedFlag;

}

