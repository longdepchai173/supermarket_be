package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateShelfRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.ShelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shelves")
public class ShelfController {
    private final ShelfService service;
    @GetMapping("")
    public ResponseEntity<ReturnResponse> getAllShelf() {
        ReturnResponse response = service.getAll();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @PostMapping("/create")
    public ResponseEntity<ReturnResponse> create(@RequestBody CreateShelfRequest request) {
        ReturnResponse response = service.create(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
