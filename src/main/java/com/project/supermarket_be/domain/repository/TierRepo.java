package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.model.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TierRepo extends JpaRepository<Tier, Long> {
    @Query(value = "select * from Tier e where e.shelf_id = :shelfId and deleted_flag = false", nativeQuery = true)
    List<Tier> getAllTierByShelfId(@Param("shelfId") Long shelfId);
}
