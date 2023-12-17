package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepo extends JpaRepository<Provider, Long> {
    Optional<Provider> findById(Long id);
}
