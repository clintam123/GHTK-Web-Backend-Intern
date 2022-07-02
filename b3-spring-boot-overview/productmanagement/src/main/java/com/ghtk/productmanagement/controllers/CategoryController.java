//package com.ghtk.productmanagement.controllers;
//
//import com.ghtk.productmanagement.models.entities.CategoryEntity;
//import com.ghtk.productmanagement.repositories.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.persistence.EntityNotFoundException;
//
//@RestController
//@RequestMapping("/api/v1.0/category")
//public class CategoryController {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @GetMapping("")
//    public ResponseEntity<Page<CategoryEntity>> get(
//            @RequestParam(value = "page") int page,
//            @RequestParam(value = "page_size") int pageSize
//    ) {
//        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(PageRequest.of(page, pageSize));
//
//        return ResponseEntity.ok(categoryEntities);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryEntity> getById(
//            @PathVariable Integer id
//    ) {
//        CategoryEntity category = categoryRepository.findById(id).orElse(null);
//        return ResponseEntity.ok(category);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<CategoryEntity> insert(
//            @RequestBody CategoryEntity categoryEntity
//    ) {
//        return ResponseEntity.ok(categoryRepository.save(categoryEntity));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CategoryEntity> update(
//            @PathVariable Integer id,
//            @RequestBody CategoryEntity newCategory
//    ) {
//        return ResponseEntity.ok(
//                categoryRepository.findById(id)
//                        .map(category -> {
//                            category.setName(newCategory.getName());
//                            category.setStatus(newCategory.getStatus());
//                            category.setCode(newCategory.getCode());
//                            category.setDescription(newCategory.getDescription());
//                            return categoryRepository.save(category);
//                        }).orElseGet(() -> {
//                    newCategory.setId(id);
//                    return categoryRepository.save(newCategory);
//                })
//        );
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(
//            @PathVariable Integer id
//    ) {
//        try {
//            categoryRepository.deleteById(id); // call Spring's Data JPA repository method deleteById
//        } catch (EmptyResultDataAccessException ex) {
//            throw new EntityNotFoundException("Category with ID: [" + id + "] was not found");
//        }
//        return ResponseEntity.noContent().build();
//    }
//
//
//}
