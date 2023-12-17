package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProviderRepo extends JpaRepository<Provider, Long> {
    Optional<Provider> findById(Long id);
    @Query(value = "select * from Provider e where e.deleted_flag = false", nativeQuery = true)
    List<Provider> findProviderNotDeleted();
}
