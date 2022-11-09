package com.supportportal.service;

import com.supportportal.model.Neighbourhood;

import java.util.List;

public interface NeighbourhoodService {
    List<Neighbourhood> getAll();
    Neighbourhood insert(Neighbourhood neighbourhood);
    Neighbourhood update(Long neighbourhoodId, Neighbourhood neighbourhood);
    void deleteById(Long neighbourhoodId);
    Neighbourhood getById(Long neighbourhoodId);
}
