package com.project.supermarket_be.api.controller;

import com.project.supermarket_be.api.dto.response.DashboardResponse;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService service;
    @GetMapping("/")
    public ResponseEntity<ReturnResponse> get(@RequestParam(value = "month", defaultValue = "1") Integer month,
                                                 @RequestParam(value = "year", defaultValue = "2023") Integer year){
        ReturnResponse response = service.get(month, year);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
}
