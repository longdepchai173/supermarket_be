package com.project.supermarket_be.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JoinColumn(name = "create_by_staff", referencedColumnName = "id")
    private Account createByStaff;

    @Column(name = "inventory_time")
    private Date inventoryTime;

    @Lob
    @Column(name = "staff_signature")
    private String staffSignature;

    @Column(name = "inventory_code")
    private String inventoryCode;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "deleted_flag")
    @JsonIgnore
    private boolean deletedFlag;

}