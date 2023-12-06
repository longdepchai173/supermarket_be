package com.project.supermarket_be.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    private String gender;

    private String role;

    private String status;

    @Column(name = "has_warehouse")
    private boolean hasWarehouse;

    private String address;

    @Column(name = "safety_inspection_img")
    private String safetyInspectionImg;

    private String phone;

    private String position;

    @Column(name = "has_shelf")
    private boolean hasShelf;

    @Column(name = "has_supply")
    private boolean hasSupply;

    @Column(name = "has_audit")
    private boolean hasAudit;

    @Column(name = "has_statistic")
    private boolean hasStatistic;


}
