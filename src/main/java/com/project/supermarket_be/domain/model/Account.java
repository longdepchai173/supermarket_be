package com.project.supermarket_be.domain.model;

import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    private Integer status;

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

    @Column(name = "deleted_flag")
    private boolean deleteFlag;


    public static Account fromCreateStaffRequest(CreateStaffRequest request) {
        return Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .gender(String.valueOf(request.isGender()))
                .role(request.getRole())
                .phone(request.getPhone())
                .position(request.getPosition())
                .hasWarehouse(request.isHasWarehouse())
                .hasShelf(request.isHasShelf())
                .hasSupply(request.isHasSupply())
                .hasAudit(request.isHasAudit())
                .hasStatistic(request.isHasStatistic())
                .build();
    }

}
