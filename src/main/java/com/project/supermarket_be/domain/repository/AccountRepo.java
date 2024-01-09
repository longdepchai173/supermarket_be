package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    @Query(value = "select * from Account e where e.deleted_flag = false and e.name like %:search% and status = :status", nativeQuery = true)
    List<Account> getAllAccountByCondition(@Param("search") String search, @Param("status") Integer status);
    @Query(value = "select * from Account e where e.deleted_flag = false and e.name like %:search%", nativeQuery = true)
    List<Account> getAllAccountByName(@Param("search") String search);

    @Query(value = "select count(id) from account where deleted_flag = false", nativeQuery = true)
    long count();
}
