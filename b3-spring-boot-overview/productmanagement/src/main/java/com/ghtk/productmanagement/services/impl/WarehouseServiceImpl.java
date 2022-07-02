package com.ghtk.productmanagement.services.impl;

import com.ghtk.productmanagement.constants.ProductStatus;
import com.ghtk.productmanagement.constants.WarehouseStatus;
import com.ghtk.productmanagement.models.dtos.WarehouseDto;
import com.ghtk.productmanagement.models.entities.DistrictEntity;
import com.ghtk.productmanagement.models.entities.ProvinceEntity;
import com.ghtk.productmanagement.models.entities.WarehouseEntity;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.Pagination;
import com.ghtk.productmanagement.models.responses.PagingResponse;
import com.ghtk.productmanagement.repositories.WarehouseRepository;
import com.ghtk.productmanagement.services.DistrictService;
import com.ghtk.productmanagement.services.ProvinceService;
import com.ghtk.productmanagement.services.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final DistrictService districtService;
    private final ProvinceService provinceService;
    private final ModelMapper modelMapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, DistrictService districtService, ProvinceService provinceService, ModelMapper modelMapper) {
        this.warehouseRepository = warehouseRepository;
        this.districtService = districtService;
        this.provinceService = provinceService;
        this.modelMapper = modelMapper;
    }


    @Override
    public PagingResponse getWarehouses(int page, int pageSize) {
        Page<WarehouseDto> warehouseDtos = warehouseRepository.findAll(PageRequest.of(page, pageSize))
                .map(warehouseEntity -> modelMapper.map(warehouseEntity, WarehouseDto.class));
        return PagingResponse.builder().success(true).message("Success").data(warehouseDtos.getContent())
                .pagination(Pagination.builder().page(page).pageSize(pageSize)
                        .total(warehouseDtos.getTotalElements()).build()).build();
    }

    @Override
    public CommonResponse findById(Integer id) {
        WarehouseEntity warehouseEntity = warehouseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id: " + id + "not found")
        );
        WarehouseDto warehouseDto = modelMapper.map(warehouseEntity, WarehouseDto.class);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(warehouseDto)
                .build();
    }

    @Override
    public CommonResponse insert(WarehouseDto warehouseDto) {
        DistrictEntity district = districtService.findById(warehouseDto.getDistrictId());
        ProvinceEntity province = provinceService.findById(warehouseDto.getProvinceId());
        WarehouseEntity warehouseEntity = modelMapper.map(warehouseDto, WarehouseEntity.class);
        warehouseEntity.setDistrictEntity(district);
        warehouseEntity.setProvinceEntity(province);
        WarehouseDto warehouseDtoResponse = modelMapper.map(warehouseRepository.save(warehouseEntity), WarehouseDto.class);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(warehouseDtoResponse)
                .build();
    }

    @Override
    public CommonResponse update(WarehouseDto warehouseDto, int id) {
        DistrictEntity district = districtService.findById(warehouseDto.getDistrictId());
        ProvinceEntity province = provinceService.findById(warehouseDto.getProvinceId());
        WarehouseEntity warehouse = warehouseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Warehouse with id: " + id + "not found")
        );

        warehouse.setId(id);
        warehouse.setProvinceEntity(province);
        warehouse.setDistrictEntity(district);
        warehouse.setName(warehouseDto.getName());

        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(modelMapper.map(warehouseRepository.save(warehouse), WarehouseDto.class))
                .build();
    }

    @Override
    public CommonResponse deleteById(Integer id) {
        WarehouseEntity warehouse = warehouseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Warehouse with id: " + id + "not found")
        );
        warehouse.setStatus(WarehouseStatus.INACTIVE);
        warehouseRepository.save(warehouse);
        return CommonResponse.builder()
                .success(true)
                .message("Success")
                .data(null)
                .build();
    }
}
