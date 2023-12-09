package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    Page<Account> findAll(Pageable pageable);
    Page<Account> findByNameContainingAndPositionContainingAndStatus(
            String name, String position, Integer status, Pageable page);

    Page<Account> findByNameContainingAndPositionContaining(
            String name, String position, Pageable page);

    Page<Account> findByNameContainingAndStatus(
            String name, Integer status, Pageable page);

    Page<Account> findByPositionContainingAndStatus(
            String position, Integer status, Pageable page);

    Page<Account> findByPositionContainingAndNameContaining(
            String position, String name, Pageable page);

    Page<Account> findByPositionContaining(
            String position, Pageable page);

    Page<Account> findByStatus(
            Integer status, Pageable page);

    Page<Account> findByNameContaining(
            String name, Pageable page);

}
