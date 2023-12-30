package com.project.supermarket_be.domain.repository;

import com.project.supermarket_be.domain.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepo extends JpaRepository<Shelf, Long> {
}
