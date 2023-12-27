package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.response.ReturnResponse;

public interface TierService {
    ReturnResponse getAllTierByShelfId(String shelfId);
}
