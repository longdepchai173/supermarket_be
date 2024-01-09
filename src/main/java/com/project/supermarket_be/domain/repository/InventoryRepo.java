package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    @Modifying
    @Query(value = "INSERT INTO product_inventory (product_id, inventory_id, status, quantity) " +
            "VALUES (:productId, :inventoryId, :status, :quantity)", nativeQuery = true)
    void storeDataIn(@Param("productId")Integer productId, @Param("inventoryId")Integer inventoryId,
                     @Param("status")String status, @Param("quantity")Integer quantity);
    @Query(value = "select i.inventory_id, inventory_code, inventory_time,\n" +
            "a.name\n" +
            "from inventory i\n" +
            "inner join account a on a.id = i.create_by_staff\n" +
            "where (inventory_code like %:search%\n" +
            "or a.name like %:search%)\n" +
            "and i.deleted_flag = false\n" +
            "and i.inventory_time >= TO_TIMESTAMP(:from, 'YYYY-MM-DD HH24:MI:SS')\n" +
            "and i.inventory_time <= TO_TIMESTAMP(:to, 'YYYY-MM-DD HH24:MI:SS')" , nativeQuery = true)
    List<Object[]> getAll(@Param("search") String search, @Param("from") String from, @Param("to") String to);

    @Query(value = "select count(inventory_id) from inventory where deleted_flag = false", nativeQuery = true)
    long count();
}
