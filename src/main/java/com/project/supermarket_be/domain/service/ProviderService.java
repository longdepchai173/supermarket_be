package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.request.CreateProviderRequest;
import com.project.supermarket_be.api.dto.request.CreateStaffRequest;
import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Provider;

public interface ProviderService {
    Provider findById(Long id);
    ReturnResponse createProvider(CreateProviderRequest request);
    ReturnResponse update(CreateProviderRequest request, String id);
    ReturnResponse deleteById(String id);
    ReturnResponse getAllProvider();
}
