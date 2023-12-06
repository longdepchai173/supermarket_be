package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
