package com.project.supermarket_be.domain.service.impl;

import com.project.supermarket_be.api.exception.customerException.CanNotFoundProvider;
import com.project.supermarket_be.domain.model.Provider;
import com.project.supermarket_be.domain.repository.ProviderRepo;
import com.project.supermarket_be.domain.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepo repo;
    @Override
    public Provider findById(Long id) {
        return repo.findById(id).orElseThrow(()->new CanNotFoundProvider(String.valueOf(id)));
    }
}
