package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CategoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<ReturnResponse> updateCategory(@RequestBody CategoryRequest request, @PathVariable String id){
        ReturnResponse response = service.update(request, id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<ReturnResponse> deleteCategory(@PathVariable String id){
        ReturnResponse response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("all")
    public ResponseEntity<ReturnResponse> getAllCategory(){
        ReturnResponse response = service.getAll();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
