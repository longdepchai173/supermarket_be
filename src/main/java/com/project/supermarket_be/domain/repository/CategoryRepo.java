package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
    @Query(value = "select * from Category e where e.deleted_flag = false", nativeQuery = true)
    List<Category> getAllCategory();
}
