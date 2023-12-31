package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    @Modifying
    @Query(value = "INSERT INTO product_inventory (product_id, inventory_id, status, quantity) " +
            "VALUES (:productId, :inventoryId, :status, :quantity)", nativeQuery = true)
    void storeDataIn(@Param("productId")Integer productId, @Param("inventoryId")Integer inventoryId,
                     @Param("status")String status, @Param("quantity")Integer quantity);

}
