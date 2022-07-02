package com.ghtk.productmanagement.services;

import com.ghtk.productmanagement.models.dtos.ProductDto;
import com.ghtk.productmanagement.models.entities.ProductEntity;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    PagingResponse getProducts(int page, int pageSize);
    CommonResponse findById(Integer id);
    CommonResponse insert(ProductDto productDto);
    CommonResponse update(ProductDto productDto, int id);
    CommonResponse deleteById(Integer id);
    PagingResponse search(Integer page, Integer pageSize, String name, float price);
}
