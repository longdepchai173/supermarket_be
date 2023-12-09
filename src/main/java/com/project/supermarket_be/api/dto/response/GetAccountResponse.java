package com.project.supermarket_be.api.dto.response;

import com.project.supermarket_be.domain.model.Account;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class GetAccountResponse {
    private Integer currentPage;
    private Integer totalPage;
    private List<Account> accounts;
}
