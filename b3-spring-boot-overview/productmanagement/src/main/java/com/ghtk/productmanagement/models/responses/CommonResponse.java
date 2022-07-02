package com.ghtk.productmanagement.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {
    private boolean success;
    private String message;
    private Object data;
    private Object errors;
}
