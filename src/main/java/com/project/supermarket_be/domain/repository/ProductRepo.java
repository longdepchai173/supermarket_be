package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.api.dto.mapping_output.GetAllProductByConditionDto;
import com.project.supermarket_be.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "select e.product_name, e.category_id from Product e where e.id = :productId and deleted_flag = false", nativeQuery = true)
    List<Object[]> getProductName(@Param("productId") Long productId);

    @Query(value = "SELECT " +
            "product.id as product_id," +
            "warehouse_invoice.supply_code," +
            "warehouse_invoice.receiving_time," +
            "category.name," +
            "batch_code," +
            "input_quantity," +
            "sold_quantity\n" +
            "FROM product " +
            "INNER JOIN category ON category.category_id = product.category_id " +
            "INNER JOIN warehouse_invoice ON warehouse_invoice.id = product.invoice_id " +
            "WHERE (category.name LIKE %:search% " +
            "OR warehouse_invoice.supply_code LIKE %:search% " +
            "OR product_code LIKE %:search% " +
            "OR batch_code LIKE %:search%) " +
            "AND warehouse_invoice.receiving_time >= TO_TIMESTAMP(:from, 'YYYY-MM-DD HH24:MI:SS') " +
            "AND warehouse_invoice.receiving_time <= TO_TIMESTAMP(:to, 'YYYY-MM-DD HH24:MI:SS')", nativeQuery = true)
    List<Object[]> getProductsByCondition(@Param("search") String search, @Param("from")String from, @Param("to")String to);

    default List<GetAllProductByConditionDto> getProductsInfoByCondition(String search, String from, String to) {
        List<Object[]> results = getProductsByCondition(search, from, to);
        return results.stream()
                .map(result -> new GetAllProductByConditionDto(
                        (Integer) result[0],      // product_id
                        (String) result[1],       // supply_code
                        (Date) result[2],         // receiving_time
                        (String) result[3],       // category_name
                        (String) result[4],       // batch_code
                        (int) result[5],          // input_quantity
                        (int) result[6]           // sold_quantity
                ))
                .collect(Collectors.toList());
    }
    @Query(value = "Select shelf_qnt from product where id = :productId",nativeQuery = true)
    Integer getShelfQuantity(@Param("productId") Long productId);
}