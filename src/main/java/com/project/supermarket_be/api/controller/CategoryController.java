package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CategoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;
    @PostMapping("/create")
    public ResponseEntity<ReturnResponse> createCategory(@RequestBody CategoryRequest request){
        ReturnResponse response = service.create(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
