package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateInventoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService service;
    @PostMapping("/create")
    public ResponseEntity<ReturnResponse> create(@RequestBody CreateInventoryRequest request, @RequestHeader("Authorization") String token){

        ReturnResponse response = service.create(request, token);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
