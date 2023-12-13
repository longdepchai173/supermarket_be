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
@Table(name = "COMPARTMENT")
public class Compartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tier_id", referencedColumnName = "id")
    private Tier tier;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "compartment_code")
    private String compartmentCode;

    @Column(name = "current_quantity")
    private Integer currentQuantity;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;

}