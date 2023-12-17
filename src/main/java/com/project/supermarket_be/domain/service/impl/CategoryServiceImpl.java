package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.request.CategoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CategoryNotFound;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.repository.CategoryRepo;
import com.project.supermarket_be.domain.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo repo;
    @Override
    public Category findById(Long id) {
        Category result =  repo.findById(id).orElseThrow(()-> new CategoryNotFound(String.valueOf(id)));
        return result;
    }

    @Override
    public ReturnResponse create(CategoryRequest request) {
        Category category = Category
                .builder()
                .name(request.getName())
                .build();
        Category categorySaved = repo.save(category);
        return ReturnResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .data(categorySaved)
                .build();
    }
}
