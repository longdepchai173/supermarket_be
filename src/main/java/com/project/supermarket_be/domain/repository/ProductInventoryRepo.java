package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInventoryRepo extends JpaRepository<ProductInventory, Long> {
    @Modifying
    @Query(value = "INSERT INTO product_inventory (product_id, inventory_id, status, quantity) " +
            "VALUES (:productId, :inventoryId, :status, :quantity)", nativeQuery = true)
    void storeData(@Param("productId")Integer productId, @Param("inventoryId")Integer inventoryId,
                     @Param("status")String status, @Param("quantity")Integer quantity);
    @Query(value = "select  \n" +
            "            p.id as product_id, \n" +
            "            c.name as category_name,\n" +
            "            p.product_name, \n" +
            "            pi.quantity, \n" +
            "            pi.status, \n" +
            "            p.expired_date, \n" +
            "            p.product_code, \n" +
            "            pi.product_inventory_id " +
            "            from product p \n" +
            "            inner join category c on c.category_id = p.category_id \n" +
            "            inner join product_inventory pi on pi.product_id = p.id \n" +
            "            where pi.inventory_id = :inventoryId and pi.deleted_flag = false", nativeQuery = true)
    List<Object[]> getProductInInventory(@Param("inventoryId") Integer inventoryId);
}
