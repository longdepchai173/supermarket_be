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
@Table(name = "TIER")
public class Tier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shelf_id", referencedColumnName = "id")
    private Shelf shelf;

    @Column(name = "tier_code")
    private String tierCode;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;
}
