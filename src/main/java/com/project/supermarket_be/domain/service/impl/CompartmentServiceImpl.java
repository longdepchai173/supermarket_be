package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.domain.model.Compartment;
import com.project.supermarket_be.domain.repository.CompartmentRepo;
import com.project.supermarket_be.domain.service.CompartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompartmentServiceImpl implements CompartmentService {
    private final CompartmentRepo repo;
    @Override
    public List<Compartment> getAllCompartmentByTierId(Long tierId) {

        return null;
    }
    public float calculateInUse(Long tierId){
        float inUse;
        Integer count = 0;
        List<Object[]> compartments = repo.getAllCompartmentByTierId(tierId);
        for (Object[] row : compartments){
            Integer compartmentId = (Integer) row[0];
            Integer current_quantity = (Integer) row[1];
            if(current_quantity == 0){
                count++;
            }
        }

        return ((float)count / compartments.size()) * 100;
    }
}
