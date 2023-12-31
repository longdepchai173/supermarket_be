package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TierCountInUse {
    private Integer tierId;
    private Long count;
}
