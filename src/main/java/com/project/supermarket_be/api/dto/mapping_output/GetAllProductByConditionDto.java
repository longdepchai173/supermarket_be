package com.project.supermarket_be.api.dto.mapping_output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
public class GetAllProductByConditionDto {
        private Integer productId;
        private String supplyCode;
        private Date receivingTime;
        private String categoryName;
        private String batchCode;
        private Integer inputQuantity;
        private Integer soldQuantity;
        private BigDecimal input_price;
        //input_price
        private Integer shelfQnt;
        //shelf_qnt
        private Integer shelfArrangeQnt;
        //shelf_arrange_qnt
        private Boolean isDisable;
        //is_disable
        private Date disableDate;
        //disable_date
        private Date expiredDate;
        //expired_date
        private Date manufactureDate;
        //manufacture_date
        private String productName;
        private Integer quantity;
}
