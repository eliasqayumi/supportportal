package com.supportportal.repository;

import com.supportportal.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City deleteCityById(Long cityId);
}
