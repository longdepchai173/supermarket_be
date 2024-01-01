package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.response.ReturnResponse;
import com.project.supermarket_be.domain.model.Shelf;
import com.project.supermarket_be.domain.service.impl.TierInCreateShelfRequest;

import java.util.List;

public interface TierService {
    ReturnResponse getAllTierByShelfId(String shelfId);

    void createTierByShelfId(Shelf shelf, List<TierInCreateShelfRequest> tiers);
}
