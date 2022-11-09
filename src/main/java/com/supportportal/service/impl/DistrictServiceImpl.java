package com.supportportal.service.impl;

import com.supportportal.model.District;
import com.supportportal.repository.DistrictRepository;
import com.supportportal.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    private DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public List<District> getAll() {
        return this.districtRepository.findAll();
    }

    @Override
    public District insert(District district) {

        return this.districtRepository.save(district);
    }

    @Override
    public District update(Long districtId, District district) {
        District updateDistrict = this.districtRepository.getById(districtId);
        updateDistrict.setName(district.getName());
        updateDistrict.setCity(district.getCity());
        return this.districtRepository.save(updateDistrict);
    }

    @Override
    public void deleteById(Long districtId) {
        this.districtRepository.deleteById(districtId);
    }

    @Override
    public District getById(Long districtId) {
        return this.districtRepository.getById(districtId);
    }
}
