package com.ghtk.productmanagement.services.impl;

import com.ghtk.productmanagement.models.entities.ProvinceEntity;
import com.ghtk.productmanagement.repositories.ProvinceRepository;
import com.ghtk.productmanagement.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public ProvinceEntity findById(int id) {
        return provinceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Province with id: " + id + " not found")
        );
    }
}
