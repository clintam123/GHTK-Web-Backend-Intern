package com.ghtk.productmanagement.services.impl;

import com.ghtk.productmanagement.models.entities.DistrictEntity;
import com.ghtk.productmanagement.repositories.DistrictRepository;
import com.ghtk.productmanagement.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }


    @Override
    public DistrictEntity findById(int id) {
        return districtRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("District with id: " + id + " not found")
        );
    }
}
