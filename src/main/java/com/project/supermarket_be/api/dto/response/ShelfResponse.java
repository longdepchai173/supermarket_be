package com.project.supermarket_be.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShelfResponse {
    private Integer id;
    private Integer categoryId;
    private String shelfCode;
}
