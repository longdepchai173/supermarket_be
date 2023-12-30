package com.project.supermarket_be.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class AddProductToShelfRequest {
    Integer tierId;
    Integer productId;
    List<Integer> compartmentIds;
}
