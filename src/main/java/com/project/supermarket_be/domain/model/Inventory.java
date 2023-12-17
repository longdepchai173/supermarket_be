package com.project.supermarket_be.domain.model;

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
@Table(name = "INVENTORY")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "create_by_staff", referencedColumnName = "id")
    private Account createdByStaff;

    @Column(name = "inventory_qnt")
    private Integer inventoryQuantity;

    @Column(name = "product_status", length = 20)
    private String productStatus;

    @Lob
    @Column(name = "staff_signature")
    private byte[] staffSignature;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "deleted_flag")
    @JsonIgnore
    private boolean deletedFlag;

}