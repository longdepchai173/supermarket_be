package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.CompartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/clear/{compartmentId}")
    public ResponseEntity<ReturnResponse> clearCompartment(@PathVariable String compartmentId){
        ReturnResponse response = compartmentService.clear(Long.valueOf(compartmentId));
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
