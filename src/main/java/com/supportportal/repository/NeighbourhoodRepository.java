package com.supportportal.repository;

import com.supportportal.model.Neighbourhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighbourhoodRepository extends JpaRepository<Neighbourhood,Long> {
    Neighbourhood deleteNeighbourhoodById(Long neighbourhoodId);
}
