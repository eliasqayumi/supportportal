package com.supportportal.repository;

import com.supportportal.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    District deleteDistrictById(Long districtId);
}
