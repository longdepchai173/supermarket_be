package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.domain.model.Compartment;

import java.util.List;

public interface CompartmentService {
    List<Compartment> getAllCompartmentByTierId(Long tierId);
    float calculateInUse(Long tierId);
}
