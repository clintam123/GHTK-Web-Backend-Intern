package com.ghtk.productmanagement.repository;

import com.ghtk.productmanagement.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
