package com.project.supermarket_be.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class AuthResponse {
    @JsonIgnore
    private HttpStatus statusCode;
    private Object data;
    private String accessToken;
}
