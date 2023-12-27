package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompartmentRepo extends JpaRepository<Compartment, Long> {
    @Query(value = "select e.id, e.current_quantity from Compartment e where e.tier_id = :tierId and deleted_flag = false", nativeQuery = true)
    List<Object[]> getAllCompartmentByTierId(@Param("tierId") Long tierId);
}
