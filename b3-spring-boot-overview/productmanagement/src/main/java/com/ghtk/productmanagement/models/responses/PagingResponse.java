package com.ghtk.productmanagement.models.responses;

import lombok.*;

@Data
@Builder
public class PagingResponse {
    private boolean success;
    private String message;
    private Object data;
    private Pagination pagination;
    private Object[] errors;
}
