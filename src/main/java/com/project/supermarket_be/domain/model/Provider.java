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
@Table(name = "PROVIDER")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "phone_contact")
    private String phoneContact;

    @Column(name = "email_contact")
    private String emailContact;

    @Column(name = "address")
    private String address;

    @Column(name = "safety_inspection")
    private String safetyInspection;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
    @Column(name = "deleted_flag")
    private Boolean deletedFlag;
}
