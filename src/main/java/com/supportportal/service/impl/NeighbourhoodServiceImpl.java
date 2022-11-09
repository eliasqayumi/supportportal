package com.supportportal.service.impl;

import com.supportportal.model.Neighbourhood;
import com.supportportal.repository.NeighbourhoodRepository;
import com.supportportal.service.NeighbourhoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeighbourhoodServiceImpl implements NeighbourhoodService {
    private NeighbourhoodRepository neighbourhoodRepository;

    public NeighbourhoodServiceImpl(NeighbourhoodRepository neighbourhoodRepository) {
        this.neighbourhoodRepository = neighbourhoodRepository;
    }

    @Override
    public List<Neighbourhood> getAll() {
        return this.neighbourhoodRepository.findAll();
    }

    @Override
    public Neighbourhood insert(Neighbourhood neighbourhood) {
        return this.neighbourhoodRepository.save(neighbourhood);
    }

    @Override
    public Neighbourhood update(Long neighbourhoodId, Neighbourhood neighbourhood) {
        Neighbourhood updateNeighbourhood = this.neighbourhoodRepository.getById(neighbourhoodId);
        updateNeighbourhood.setName(neighbourhood.getName());
        updateNeighbourhood.setDistrict(neighbourhood.getDistrict());
        return this.neighbourhoodRepository.save(updateNeighbourhood);
    }

    @Override
    public void deleteById(Long neighbourhoodId) {
         this.neighbourhoodRepository.deleteById(neighbourhoodId);
    }

    @Override
    public Neighbourhood getById(Long neighbourhoodId) {
        return this.neighbourhoodRepository.getById(neighbourhoodId);
    }
}
