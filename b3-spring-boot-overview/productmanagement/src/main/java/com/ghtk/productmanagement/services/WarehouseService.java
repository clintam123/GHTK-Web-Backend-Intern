package com.ghtk.productmanagement.services;

import com.ghtk.productmanagement.models.dtos.CategoryDto;
import com.ghtk.productmanagement.models.dtos.WarehouseDto;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.PagingResponse;

public interface WarehouseService {
    PagingResponse getWarehouses(int page, int pageSize);

    CommonResponse findById(Integer id);

    CommonResponse insert(WarehouseDto warehouseDto);

    CommonResponse update(WarehouseDto warehouseDto, int id);

    CommonResponse deleteById(Integer id);
}
