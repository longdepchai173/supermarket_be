package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.request.CreateProviderRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
