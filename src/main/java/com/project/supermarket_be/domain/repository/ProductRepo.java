package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.api.dto.mapping_output.GetAllProductByConditionDto;
import com.project.supermarket_be.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
            "sold_quantity, " +
            "input_price, " +
            "shelf_qnt, "+
            "shelf_arrange_qnt, "+
            "is_disable, "+
            "disable_date, "+
            "expired_date, " +
            "manufacture_date,\n"+
            "product_name "+
            "FROM product " +
            "INNER JOIN category ON category.category_id = product.category_id " +
            "INNER JOIN warehouse_invoice ON warehouse_invoice.id = product.invoice_id " +
            "WHERE (category.name LIKE %:search% " +
            "OR warehouse_invoice.supply_code LIKE %:search% " +
            "OR product_code LIKE %:search% " +
            "OR batch_code LIKE %:search%) " +
            "AND warehouse_invoice.receiving_time >= TO_TIMESTAMP(:from, 'DD-MM-YYYY HH24:MI:SS') " +
            "AND warehouse_invoice.receiving_time <= TO_TIMESTAMP(:to, 'DD-MM-YYYY HH24:MI:SS')", nativeQuery = true)
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
                        (Integer) result[5],          // input_quantity
                        (Integer) result[6] ,         // sold_quantity
                        (BigDecimal) result[7],        //input_price
                        (Integer) result[8],           //shelf_qnt
                        (Integer) result[9],//shelf_arrange_qnt
                        (Boolean) result[10],//is_disable
                        (Date) result[11],//disable_date
                        (Date) result[12],//expired_date
                        (Date) result[13],//manufacture_date
                        (String) result[14],
                        ((Integer) result[5] - (Integer) result[6] - (Integer) result[8])
                ))
                .collect(Collectors.toList());
    }
    @Query(value = "Select shelf_qnt from product where id = :productId",nativeQuery = true)
    Integer getShelfQuantity(@Param("productId") Long productId);
}