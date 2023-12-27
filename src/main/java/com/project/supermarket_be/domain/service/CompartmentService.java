package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Compartment;

import java.util.List;

public interface CompartmentService {
    ReturnResponse getAllCompartmentByTierId(String tierId);
    float calculateInUse(Long tierId);
}
