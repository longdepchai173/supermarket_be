package com.project.supermarket_be.domain.service;

import com.project.supermarket_be.api.dto.response.ReturnResponse;

public interface DashboardService {

    ReturnResponse get(Integer month, Integer year);
}
