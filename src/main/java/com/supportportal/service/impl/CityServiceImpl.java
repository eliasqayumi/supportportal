package com.supportportal.service.impl;

import com.supportportal.model.City;
import com.supportportal.repository.CityRepository;
import com.supportportal.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City insert(City city)  {
        return this.cityRepository.save(city);
    }

    @Override
    public City update(Long cityId, City city) {
        City updateCity=this.cityRepository.getById(cityId);
        updateCity.setName(city.getName());
        return this.cityRepository.save(updateCity);
    }

    @Override
    public void deleteById(Long cityId) {
        this.cityRepository.deleteById(cityId);
    }

    @Override
    public City getById(Long cityId) {
        return this.cityRepository.getById(cityId);
    }
}
