package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateInventoryRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @GetMapping("/list")
    public ResponseEntity<ReturnResponse> getAll(@RequestParam(name = "search", defaultValue = "") String search,
                                                 @RequestParam(name = "from", defaultValue = "0001-01-01") String from,
                                                 @RequestParam(name = "to", defaultValue = "2100-01-01") String to){

        ReturnResponse response = service.getAll(search, from, to);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/{inventoryId}")
    public ResponseEntity<ReturnResponse> getById(@PathVariable("inventoryId")Integer inventoryId){

        ReturnResponse response = service.getById(inventoryId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("delete/{inventoryId}")
    public ResponseEntity<ReturnResponse> deleteById(@PathVariable("inventoryId")Integer inventoryId){

        ReturnResponse response = service.deleteById(inventoryId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
