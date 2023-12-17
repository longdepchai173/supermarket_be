package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateProviderRequest;
import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/provides")
public class ProviderController {
    private final ProviderService service;
    @PostMapping("/create")
    public ResponseEntity<ReturnResponse> createProvider(@RequestBody CreateProviderRequest request){
        ReturnResponse response = service.createProvider(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReturnResponse> updateProvider(@RequestBody CreateProviderRequest request, @PathVariable String id){
        ReturnResponse response = service.update(request, id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ReturnResponse> deleteProvider(@PathVariable String id){
        ReturnResponse response = service.deleteById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
