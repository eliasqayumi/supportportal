package com.supportportal.service;

import com.supportportal.model.HomeType;

import java.util.List;

public interface HomeTypeService {
    List<HomeType> getAll();
    HomeType insert(HomeType homeType);
    HomeType update(Long homeTypeId,HomeType homeType);
    void deleteById(Long homeTypeId);
    HomeType getById(Long homeTypeId);
}
