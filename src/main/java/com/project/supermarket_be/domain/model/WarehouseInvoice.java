package com.project.supermarket_be.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WAREHOUSE_INVOICE")
public class WarehouseInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Account staff;

    @Column(name = "receiving_time")
    private Date receivingTime;

    @Column(name = "supply_code")
    private String supplyCode;

    @Column(name = "delivery_person_name")
    private String deliveryPersonName;

    @Column(name = "receiving_clerk_name")
    private String receivingClerkName;

    @Lob
    @Column(name = "delivery_signature")
    private String deliverySignature;

    @Lob
    @Column(name = "receiving_signature")
    private String receivingSignature;

    @Column(name = "note")
    private String note;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;
}
