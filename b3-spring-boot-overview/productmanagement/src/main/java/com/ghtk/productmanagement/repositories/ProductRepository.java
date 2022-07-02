package com.ghtk.productmanagement.repositories;

import com.ghtk.productmanagement.models.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


    Page<ProductEntity> findAllByNameContainingAndPriceGreaterThan(String name, Float price, Pageable pageable);
}
