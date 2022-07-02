package com.ghtk.productmanagement.services.impl;

import com.ghtk.productmanagement.constants.ProductStatus;
import com.ghtk.productmanagement.models.dtos.ProductDto;
import com.ghtk.productmanagement.models.entities.CategoryEntity;
import com.ghtk.productmanagement.models.entities.ProductEntity;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.Pagination;
import com.ghtk.productmanagement.models.responses.PagingResponse;
import com.ghtk.productmanagement.repositories.ProductRepository;
import com.ghtk.productmanagement.services.CategoryService;
import com.ghtk.productmanagement.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }


    @Override
    public PagingResponse getProducts(int page, int pageSize) {
        Page<ProductDto> productDtos = productRepository.findAll(PageRequest.of(page, pageSize))
                .map(productEntity -> modelMapper.map(productEntity, ProductDto.class));
        return PagingResponse.builder().success(true).message("Thanh cong").data(productDtos.getContent())
                .pagination(Pagination.builder().page(page).pageSize(pageSize)
                        .total(productDtos.getTotalElements()).build()).build();
    }

    @Override
    public CommonResponse findById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id: " + id + "not found")
        );
        ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(productDto)
                .build();
    }

    @Override
    public CommonResponse insert(ProductDto productDto) {
        CategoryEntity category = categoryService.checkIdCategoryIsPresent(productDto.getCategory().getId());
        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        productEntity.setCategory(category);
        ProductDto productDtoResponse = modelMapper.map(productRepository.save(productEntity), ProductDto.class);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(productDtoResponse)
                .build();
    }

    @Override
    public CommonResponse update(ProductDto productDto, int id) {
        CategoryEntity category = categoryService.checkIdCategoryIsPresent(productDto.getCategory().getId());
        ProductEntity product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id: " + id + "not found")
        );

        product.setId(id);
        product.setCategory(category);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(modelMapper.map(productRepository.save(product), ProductDto.class))
                .build();
    }

    @Override
    public CommonResponse deleteById(Integer id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id: " + id + "not found")
        );
        product.setStatus(ProductStatus.INACTIVE);
        productRepository.save(product);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(null)
                .build();
    }

    @Override
    public PagingResponse search(Integer page, Integer pageSize, String name, float price) {
        Pageable sortedByPriceDescending = PageRequest.of(page, pageSize, Sort.by("price").descending());
        Page<ProductDto> productDtos = productRepository.
                findAllByNameContainingAndPriceGreaterThan(name, price, sortedByPriceDescending).map(
                        productEntity -> modelMapper.map(productEntity, ProductDto.class)
                );
        return PagingResponse.builder().success(true).message("Thanh cong").data(productDtos.getContent())
                .pagination(Pagination.builder().page(page).pageSize(pageSize)
                        .total(productDtos.getTotalElements()).build()).build();
    }
}
