package com.project.supermarket_be.api.dto.parameter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllAccountParam {
    private Integer pageNumber;
    private Integer limit;
    private String search;
    private Integer status;
    private String position;
}
