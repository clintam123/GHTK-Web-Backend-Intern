package com.ghtk.productmanagement.services.impl;

import com.ghtk.productmanagement.constants.CategoryStatus;
import com.ghtk.productmanagement.models.dtos.CategoryDto;
import com.ghtk.productmanagement.models.entities.CategoryEntity;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.Pagination;
import com.ghtk.productmanagement.models.responses.PagingResponse;
import com.ghtk.productmanagement.repositories.CategoryRepository;
import com.ghtk.productmanagement.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagingResponse getCategories(int page, int pageSize) {
        Page<CategoryDto> categoryDtos = categoryRepository.findAll(PageRequest.of(page, pageSize))
                .map(productEntity -> modelMapper.map(productEntity, CategoryDto.class));
        return PagingResponse.builder().success(true).message("Success").data(categoryDtos.getContent())
                .pagination(Pagination.builder().page(page).pageSize(pageSize)
                        .total(categoryDtos.getTotalElements()).build()).build();
    }

    @Override
    public CommonResponse findById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with id: " + id + "not found")
        );
        CategoryDto categoryDto = modelMapper.map(categoryEntity, CategoryDto.class);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(categoryDto)
                .build();
    }

    @Override
    public CommonResponse save(CategoryDto categoryDto) {
        CategoryEntity category = modelMapper.map(categoryDto, CategoryEntity.class);
        CategoryDto categoryDtoResponse = modelMapper.map(categoryRepository.save(category), CategoryDto.class);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(categoryDtoResponse)
                .build();
    }

    @Override
    public CommonResponse update(CategoryDto categoryDto) {
        CategoryEntity category = modelMapper.map(categoryDto, CategoryEntity.class);
        CategoryDto categoryDtoResponse = modelMapper.map(categoryRepository.save(category), CategoryDto.class);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(categoryDtoResponse)
                .build();
    }

    @Override
    public CommonResponse deleteById(Integer id) {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with id: " + id + "not found")
        );
        category.setStatus(CategoryStatus.INACTIVE);
        categoryRepository.save(category);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(null)
                .build();
    }

    public CategoryEntity checkIdCategoryIsPresent(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with id: " + id + "not found")
        );
    }
}
