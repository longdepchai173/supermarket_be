package com.project.supermarket_be.api.dto.parameter;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class GetAllProductParam {
    private Integer pageNumber;
    private Integer limit;
    private String search;
    private String from;
    private String to;
}
