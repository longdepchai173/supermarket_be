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
    @Query(value = "Select tier.id, category_id, shelf_code \n" +
            "            from shelf\n" +
            "\t\t\tleft join tier on tier.shelf_id = shelf.id\n" +
            "           where shelf.deleted_flag = false", nativeQuery = true)
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
    @Query(value = "\t\t   select tier.id, count(compartment.id) as count\n" +
            "\t\t   from tier\n" +
            "\t\t   left join compartment on compartment.tier_id = tier.id\n" +
            "\t\t   left join shelf on tier.shelf_id = shelf.id\n" +
            "\t\t   where shelf.id = :shelfId\n" +
            "\t\t   group by tier.id", nativeQuery = true)
    List<Object[]> calculateInUse(@Param("shelfId") Integer shelfId);
}
