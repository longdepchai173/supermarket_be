package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.api.dto.response.ShelfResponse;
import com.project.supermarket_be.domain.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface ShelfRepo extends JpaRepository<Shelf, Long> {
    @Query(value = "Select id, category_id, shelf_code " +
            "from shelf " +
            "where deleted_flag = false", nativeQuery = true)
    List<Object[]> getAllRepo();
    default List<ShelfResponse> getAll(){
        List<Object[]> results = getAllRepo();
        return results.stream()
                .map(result -> ShelfResponse.builder()
                                .id((Integer) result[0])
                                .shelfCode((String) result[2])
                                .categoryId((Integer) result[1])
                                .build()
                        ).collect(Collectors.toList());
    }
}
