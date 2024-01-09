package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProviderRepo extends JpaRepository<Provider, Long> {
    Optional<Provider> findById(Long id);
    @Query(value = "select * from Provider e where e.deleted_flag = false", nativeQuery = true)
    List<Provider> findProviderNotDeleted();

    @Query(value = "SELECT" +
            "    s.id AS provider_id,\n" +
            "    s.name AS company_name,\n" +
            "    SUM(p.input_price * p.input_quantity) AS total\n" +
            "FROM\n" +
            "    provider s\n" +
            "LEFT JOIN\n" +
            "    product p ON s.id = p.provider_id\n" +
            "LEFT JOIN\n" +
            "\twarehouse_invoice w ON p.invoice_id = w.id\n" +
            "WHERE \n" +
            "\tEXTRACT(MONTH FROM w.receiving_time) = :month\n" +
            "\tAND\n" +
            "\tEXTRACT(YEAR FROM w.receiving_time) = :year\n" +
            "GROUP BY\n" +
            "    s.id, s.name\n" +
            "ORDER BY\n" +
            "\ttotal DESC;", nativeQuery = true)
    List<Object[]> getDashboard(@Param("month")Integer month, @Param("year") Integer year);
    @Query(value = "select count(id) from provider where deleted_flag = false", nativeQuery = true)
    long count();
}
