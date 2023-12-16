package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.domain.model.Category;

public interface CategoryService {
    Category findById(Long id);
}
