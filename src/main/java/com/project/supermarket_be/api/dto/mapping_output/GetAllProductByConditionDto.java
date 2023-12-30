package com.project.supermarket_be.api.dto.mapping_output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
public class GetAllProductByConditionDto {
        private Integer productId;
        private String supplyCode;
        private Date receivingTime;
        private String categoryName;
        private String batchCode;
        private int inputQuantity;
        private int soldQuantity;
}
