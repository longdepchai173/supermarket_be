package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.exception.customerException.CategoryNotFound;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.repository.CategoryRepo;
import com.project.supermarket_be.domain.service.CategoryService;
import lombok.AllArgsConstructor;
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
}
