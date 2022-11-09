package com.supportportal.repository;

import com.supportportal.model.HomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeTypeRepository extends JpaRepository<HomeType, Long> {
    HomeType deleteHomeTypeById(Long homeTypeId);
}
