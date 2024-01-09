package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.api.dto.response.ShelfResponse;
import com.project.supermarket_be.domain.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public interface ShelfRepo extends JpaRepository<Shelf, Long> {
    @Query(value = "Select shelf.id, category_id, shelf_code \n" +
            "from shelf\n" +
            "where shelf.deleted_flag = false", nativeQuery = true)
    List<Object[]> getAllRepo();
    default List<ShelfResponse> getAll(){
        List<Object[]> results = getAllRepo();
        return results.stream()
                .map(result -> ShelfResponse.builder()
                                .shelfId((Integer) result[0])
                                .shelfCode((String) result[2])
                                .categoryId((Integer) result[1])
                                .build()
                        ).collect(Collectors.toList());
    }
    @Query(value = "select tier.id, sum(compartment.current_quantity) as count\n" +
            " from tier\n" +
            " left join compartment on compartment.tier_id = tier.id\n" +
            " left join shelf on tier.shelf_id = shelf.id\n" +
            " where shelf.id = :shelfId\n" +
            "group by tier.id", nativeQuery = true)
    List<Object[]> calculateInUse(@Param("shelfId") Integer shelfId);
    @Query(value = "select * from shelf where shelf_code = :shelfCode", nativeQuery = true)
    List<Object[]> findByShelfCode(@Param("shelfCode") String shelfCode);
}
