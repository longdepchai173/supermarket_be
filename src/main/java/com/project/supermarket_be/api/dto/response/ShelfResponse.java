package com.project.supermarket_be.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShelfResponse {
    private Integer shelfId;
    private Integer categoryId;
    private String shelfCode;
    private float inUse;
}
