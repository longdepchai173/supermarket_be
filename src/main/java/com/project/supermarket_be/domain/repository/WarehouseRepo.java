package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.WarehouseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface WarehouseRepo extends JpaRepository<WarehouseInvoice, Long> {
    Optional<WarehouseInvoice> findById(Long id);
}
