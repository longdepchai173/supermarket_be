package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateInventoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService service;
    @PostMapping("/create")
    public ResponseEntity<ReturnResponse> create(@RequestBody CreateInventoryRequest request){
        ReturnResponse response = service.create(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
