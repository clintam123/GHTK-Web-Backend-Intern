package com.ghtk.productmanagement.controller;

import com.ghtk.productmanagement.model.entity.CategoryEntity;
import com.ghtk.productmanagement.model.entity.ProductEntity;
import com.ghtk.productmanagement.repository.CategoryRepository;
import com.ghtk.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1.0/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity<Page<ProductEntity>> get(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ) {
        Page<ProductEntity> productEntities = productRepository.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(productEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getById(
            @PathVariable Integer id
    ) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        return ResponseEntity.ok(product);
    }

    @PostMapping("")
    public ResponseEntity<ProductEntity> insert(
            @RequestBody ProductEntity productEntity
    ) {

        CategoryEntity category = categoryRepository.findById(productEntity.getCategoryEntity().getId()).orElse(null);
        productEntity.setCategoryEntity(category);

        return ResponseEntity.ok(productRepository.save(productEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(
            @PathVariable Integer id,
            @RequestBody ProductEntity newProduct
    ) {
        return ResponseEntity.ok(
                productRepository.findById(id)
                        .map(product -> {
                            product.setName(newProduct.getName());
                            product.setStatus(newProduct.getStatus());
                            product.setPrice(newProduct.getPrice());
                            product.setDescription(newProduct.getDescription());
                            product.setSku(newProduct.getSku());

                            CategoryEntity category = categoryRepository.findById(newProduct.getCategoryEntity().getId()).orElseThrow(
                                    () -> new EntityNotFoundException("Category with ID: [" + id + "] was not found"));

                            product.setCategoryEntity(category);
                            return productRepository.save(product);
                        }).orElseThrow(() -> new EntityNotFoundException("Product with ID: [" + id + "] was not found"))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Product with ID: [" + id + "] was not found");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductEntity>> search(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "page_size") Integer pageSize
    ) {
        Pageable sortedByPriceDescending = PageRequest.of(page, pageSize, Sort.by("price").descending());
        Page<ProductEntity> productEntities = productRepository.findAllByNameContainingAndPriceGreaterThan("áo", 50000f, sortedByPriceDescending);
        return ResponseEntity.ok(productEntities);

//        List<ProductEntity> productEntityList = productRepository.findAllByNameContainingAndPriceGreaterThan("áo", 50000f);
//        return ResponseEntity.ok(productEntityList);
    }
}
