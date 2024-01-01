package com.project.supermarket_be.api.dto.request;

import com.project.supermarket_be.domain.service.impl.TierInCreateShelfRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateShelfRequest {
    private Integer categoryId;
    private String shelfCode;
    List<TierInCreateShelfRequest> tiers;
}
