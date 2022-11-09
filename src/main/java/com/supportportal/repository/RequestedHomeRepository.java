package com.supportportal.repository;

import com.supportportal.model.RequestedHomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestedHomeRepository extends JpaRepository<RequestedHomes,Long> {
}
