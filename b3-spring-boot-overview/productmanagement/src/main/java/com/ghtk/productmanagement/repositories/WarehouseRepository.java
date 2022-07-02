package com.ghtk.productmanagement.repositories;

import com.ghtk.productmanagement.models.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Integer> {
}
