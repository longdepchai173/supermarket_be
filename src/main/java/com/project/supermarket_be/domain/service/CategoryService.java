package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CategoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Category;

public interface CategoryService {
    Category findById(Long id);
    ReturnResponse create(CategoryRequest request);

    ReturnResponse update(CategoryRequest request, String id);

    ReturnResponse delete(String id);

    ReturnResponse getAll();
    String getName(Long categoryId);
}
