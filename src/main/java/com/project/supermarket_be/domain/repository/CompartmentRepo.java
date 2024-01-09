package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompartmentRepo extends JpaRepository<Compartment, Long> {
    @Query(value = "select e.id, e.current_quantity from Compartment e where e.tier_id = :tierId and deleted_flag = false", nativeQuery = true)
    List<Object[]> getCurrentQuantityOfCompartmentByTierId(@Param("tierId") Long tierId);
    @Query(value = "select\n" +
            "\t\t\te.id, e.product_id, \n" +
            "            e.compartment_code, \n" +
            "            e.current_quantity, \n" +
            "            p.batch_code,\n" +
            "            p.shelf_qnt, \n" +
            "            p.expired_date, \n" +
            "            p.manufacture_date \n" +
            "            from Compartment e\n" +
            "            left join product p on e.product_id = p.id\n" +
            "            where  e.deleted_flag = false and e.tier_id = :tierId", nativeQuery = true)
    List<Object[]> getAllCompartmentByTierId(@Param("tierId") Long tierId);

    @Query(value = "select sum(current_quantity) as current_quantity_sum " +
            "from compartment " +
            "inner join tier on tier.id = compartment.tier_id " +
            "inner join shelf on tier.shelf_id = shelf.id where shelf.id = :shelfId", nativeQuery = true)
    Integer getCurrentQuantityByShelfId(@Param("shelfId") Long shelfId);
    @Query(value = "select product_id, current_quantity " +
            "from compartment " +
            "where id = :compartmentId and tier_id = :tierId " +
            "and deleted_flag = false", nativeQuery = true)
    List<Object[]> checkCompartment(@Param("tierId") Integer tierId, @Param("compartmentId") Integer compartmentId);
}
