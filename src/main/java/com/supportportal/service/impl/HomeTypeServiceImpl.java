package com.supportportal.service.impl;

import com.supportportal.model.HomeType;
import com.supportportal.repository.HomeTypeRepository;
import com.supportportal.service.HomeTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeTypeServiceImpl implements HomeTypeService {
    private HomeTypeRepository homeTypeRepository;

    public HomeTypeServiceImpl(HomeTypeRepository homeTypeRepository) {
        this.homeTypeRepository = homeTypeRepository;
    }

    @Override
    public List<HomeType> getAll() {
        return this.homeTypeRepository.findAll();
    }

    @Override
    public HomeType insert(HomeType homeType) {
        return this.homeTypeRepository.save(homeType);
    }

    @Override
    public HomeType update(Long homeTypeId, HomeType homeType) {
        HomeType updateHomeTYpe = this.homeTypeRepository.getById(homeTypeId);
        updateHomeTYpe.setHomeType(homeType.getHomeType());
        return this.homeTypeRepository.save(homeType);
    }

    @Override
    public void deleteById(Long homeTypeId) {
         this.homeTypeRepository.deleteById(homeTypeId);
    }

    @Override
    public HomeType getById(Long homeTypeId) {
        return this.homeTypeRepository.getById(homeTypeId);
    }
}
