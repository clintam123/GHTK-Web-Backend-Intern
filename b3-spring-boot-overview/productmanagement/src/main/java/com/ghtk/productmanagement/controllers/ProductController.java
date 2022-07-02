package com.ghtk.productmanagement.controllers;

import com.ghtk.productmanagement.models.dtos.ProductDto;
import com.ghtk.productmanagement.models.responses.CommonResponse;
import com.ghtk.productmanagement.models.responses.PagingResponse;
import com.ghtk.productmanagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<PagingResponse> get(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ) {
        return new ResponseEntity<>(productService.getProducts(page, pageSize), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> insert(
            @Validated @RequestBody ProductDto productDto
    ) {
        return new ResponseEntity<>(productService.insert(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> update(
            @PathVariable Integer id,
            @Validated @RequestBody ProductDto productDto
    ) {
        return new ResponseEntity<>(productService.update(productDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<PagingResponse> search(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "page_size") Integer pageSize,
            @RequestParam String name,
            @RequestParam float price
    ) {
        return new ResponseEntity<>(productService.search(page, pageSize, name, price), HttpStatus.OK);
    }

}
