package com.project.supermarket_be.domain.model;

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
@Table(name = "PRODUCT_INVENTORY")
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_inventory_id")
    private Long productInventoryId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "status")
    private String status;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "note")
    private String note;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;

}
