package com.supportportal.service;


import com.supportportal.model.City;

import java.util.List;

public interface CityService {
    List<City> getAll();
    City insert(City city);
    City update(Long cityId,City city);
    void deleteById(Long cityId);
    City getById(Long cityId);
}