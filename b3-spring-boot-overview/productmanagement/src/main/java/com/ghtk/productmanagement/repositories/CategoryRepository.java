package com.ghtk.productmanagement.repositories;

import com.ghtk.productmanagement.models.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
