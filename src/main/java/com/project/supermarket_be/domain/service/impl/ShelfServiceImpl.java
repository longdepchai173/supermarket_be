package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CreateShelfRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Shelf;
import com.project.supermarket_be.domain.repository.ShelfRepo;
import com.project.supermarket_be.domain.service.ShelfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelfServiceImpl implements ShelfService {
    private final ShelfRepo repo;
    @Override
    public ReturnResponse getAll() {
        List<Shelf> shelves = repo.findAll();
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(shelves)
                .build();
    }

    @Override
    public ReturnResponse create(CreateShelfRequest request) {
        return null;
    }

    @Override
    public ReturnResponse update(Long id) {
        return null;
    }

    @Override
    public ReturnResponse delete(Long id) {
        return null;
    }
}
