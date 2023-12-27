package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo  extends JpaRepository<Product, Long> {
    @Query(value = "select e.product_name, e.category_id from Product e where e.id = :productId and deleted_flag = false", nativeQuery = true)
    List<Object[]> getProductName(@Param("productId")Long productId);
}
