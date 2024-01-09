package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.WarehouseInvoice;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepo extends JpaRepository<WarehouseInvoice, Long> {
    Optional<WarehouseInvoice> findById(Long id);
    @Query(value = "SELECT\n" +
            "    m.month,\n" +
            "    COALESCE(SUM(p.input_price * p.input_quantity), 0) AS money\n" +
            "FROM\n" +
            "\tmonth_of_year m\n" +
            "LEFT JOIN\n" +
            "    warehouse_invoice w ON \n" +
            "\tEXTRACT(MONTH FROM w.receiving_time) = m.month \n" +
            "\tAND EXTRACT(YEAR FROM w.receiving_time) = :year\n" +
            "LEFT JOIN\n" +
            "    product p ON w.id = p.invoice_id\n" +
            "GROUP BY\n" +
            "    m.month\n" +
            "ORDER BY\n" +
            "    m.month ASC;", nativeQuery = true)
    List<Object[]> getDashboard(@Param("year")Integer year);
}
