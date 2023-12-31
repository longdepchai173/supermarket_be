package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TierInfoResponse {
    private Long shelfId;
    private Long tierId;
    private String tierCode;
    private float inUse;
}
