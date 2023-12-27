package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{productId}")
    public ResponseEntity<ReturnResponse> getProductDetail (@PathVariable String productId){
        ReturnResponse response = productService.getProductById(productId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
