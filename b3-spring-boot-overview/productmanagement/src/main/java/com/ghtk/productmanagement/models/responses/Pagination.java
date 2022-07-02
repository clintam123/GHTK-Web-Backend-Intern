package com.ghtk.productmanagement.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
    Integer page;
    @JsonProperty("page_size")
    Integer pageSize;
    Long total;
}
