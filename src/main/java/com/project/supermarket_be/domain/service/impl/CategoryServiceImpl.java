package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.dto.mapping_output.GetAllProductByConditionDto;
import com.project.supermarket_be.api.dto.request.CategoryRequest;
import com.project.supermarket_be.api.dto.response.CategoryResponse;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.api.exception.customerException.CategoryNotFound;
import com.project.supermarket_be.domain.model.Category;
import com.project.supermarket_be.domain.repository.CategoryRepo;
import com.project.supermarket_be.domain.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public ReturnResponse update(CategoryRequest request, String id) {
        Long categoryId = Long.valueOf(id);
        Category category = repo.findById(categoryId).orElseThrow(()->new CategoryNotFound(id));
        category.setName(request.getName());
        Category categoryUpdated = repo.save(category);
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(categoryUpdated)
                .build();
    }

    @Override
    public ReturnResponse delete(String id) {
        Long categoryId = Long.valueOf(id);
        Category category = repo.findById(categoryId).orElseThrow(()->new CategoryNotFound(id));
        if(category.isDeletedFlag() == true) {
            return ReturnResponse.builder()
                    .statusCode(HttpStatus.CONFLICT)
                    .data(String.format("Category with id %s already deleled", id))
                    .build();
        }
        category.setDeletedFlag(true);
        Category categoryUpdated = repo.save(category);
        return ReturnResponse.builder()
                .statusCode(HttpStatus.OK)
                .data(categoryUpdated)
                .build();
    }

    @Override
    public ReturnResponse getAll() {
        List<Object[]> results = repo.getAllCategoryGroupByCondition();
        List<CategoryResponse> responses =  results.stream()
                .map(result -> CategoryResponse.builder()
                        .categoryId((Integer) result[0])
                        .name((String) result[1])
                        .productQnt((Long) result[2])
                        .build())
                .collect(Collectors.toList());
        return ReturnResponse.builder().statusCode(HttpStatus.OK).data(responses).build();
    }

    @Override
    public String getName(Long categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(()->new CategoryNotFound(String.valueOf(categoryId)));
        return category.getName();
    }

}
