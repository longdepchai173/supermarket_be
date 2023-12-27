package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.CompartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/compartments")
public class CompartmentController {
    private final CompartmentService compartmentService;
    @GetMapping("/{tierId}")
    public ResponseEntity<ReturnResponse> getCompartmentByTierId(@PathVariable String tierId){
        ReturnResponse response = compartmentService.getAllCompartmentByTierId(tierId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
