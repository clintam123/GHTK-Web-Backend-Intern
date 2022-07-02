package com.ghtk.productmanagement.services;

import com.ghtk.productmanagement.models.dtos.CategoryDto;
import com.ghtk.productmanagement.models.entities.CategoryEntity;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.PagingResponse;

public interface CategoryService {
    PagingResponse getCategories(int page, int pageSize);

    CommonResponse findById(Integer id);

    CommonResponse save(CategoryDto categoryDto);

    CommonResponse update(CategoryDto categoryDto);

    CommonResponse deleteById(Integer id);

    CategoryEntity checkIdCategoryIsPresent(Integer id);

}
