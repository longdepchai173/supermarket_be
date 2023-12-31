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
    @Query(value = "SELECT\n" +
            "    c.category_id,\n" +
            "    c.name,\n" +
            "    SUM(p.input_quantity - p.sold_quantity) AS product_qnt -- lấy tổng product sp thuộc category đang có trong siêu thị\n" +
            "FROM\n" +
            "    category c\n" +
            "LEFT JOIN\n" +
            "    product p ON c.category_id = p.category_id\n" +
            "WHERE \n" +
            "\tc.deleted_flag = false\t-- chỉ lấy category chưa bị xóa\n" +
            "\tAND p.input_quantity - p.sold_quantity >= 0\t-- chỉ lấy product còn số lượng trong siêu thị\n" +
            "GROUP BY\n" +
            "    c.category_id, c.name;", nativeQuery = true)
    List<Object[]> getAllCategoryGroupByCondition();

}
