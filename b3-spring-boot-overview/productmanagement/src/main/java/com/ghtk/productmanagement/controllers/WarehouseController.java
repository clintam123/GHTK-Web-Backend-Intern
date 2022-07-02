package com.ghtk.productmanagement.controllers;

import com.ghtk.productmanagement.models.dtos.WarehouseDto;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.PagingResponse;
import com.ghtk.productmanagement.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("")
    public ResponseEntity<PagingResponse> get(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ) {
        return new ResponseEntity<>(warehouseService.getWarehouses(page, pageSize), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(warehouseService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> insert(
            @Validated @RequestBody WarehouseDto warehouseDto
    ) {
        return new ResponseEntity<>(warehouseService.insert(warehouseDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> update(
            @PathVariable Integer id,
            @Validated @RequestBody WarehouseDto warehouseDto
    ) {
        return new ResponseEntity<>(warehouseService.update(warehouseDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(warehouseService.deleteById(id), HttpStatus.OK);
    }
}
