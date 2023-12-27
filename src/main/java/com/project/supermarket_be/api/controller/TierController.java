package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.TierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tiers")
public class TierController {
    private final TierService tierService;
    @GetMapping("/{shelfId}")
    public ResponseEntity<ReturnResponse> getAllTierByShelfId(@PathVariable String shelfId){
        ReturnResponse response = tierService.getAllTierByShelfId(shelfId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
