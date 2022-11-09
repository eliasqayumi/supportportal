package com.supportportal.service;


import com.supportportal.model.District;

import java.util.List;

public interface DistrictService {
    List<District> getAll();
    District insert(District district);
    District update(Long districtId,District district);
    void deleteById(Long districtId);
    District getById(Long districtId);
}
