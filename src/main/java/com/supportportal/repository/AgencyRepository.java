package com.supportportal.repository;

import com.supportportal.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long> {
    Agency deleteAgencyById(Long agencyId);
}
