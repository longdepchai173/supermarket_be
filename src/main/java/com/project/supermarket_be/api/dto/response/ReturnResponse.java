package com.project.supermarket_be.api.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
@Data
@Builder
public class ReturnResponse {
    private HttpStatusCode statusCode;
    private Object data;
}
