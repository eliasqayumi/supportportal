package com.supportportal.service.impl;

import com.supportportal.model.Agency;
import com.supportportal.repository.AgencyRepository;
import com.supportportal.service.AgencyService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgencyServiceImpl implements AgencyService {
    private AgencyRepository agencyRepository;

    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public List<Agency> getAll() {
        return this.agencyRepository.findAll();
    }

    @Override
    public Agency insert(Agency agency) {
       return this.agencyRepository.save(agency);
    }

    @Override
    public Agency update(Long agencyId, Agency agency) {
        Agency updateAgency = this.agencyRepository.getById(agencyId);
        updateAgency.setAgencyName(agency.getAgencyName());
        updateAgency.setNeighbourhoodId(agency.getNeighbourhoodId());
         return this.agencyRepository.save(updateAgency);
    }

    @Override
    public void deleteById(Long agencyId) {
         this.agencyRepository.deleteById(agencyId);
    }

    @Override
    public Agency getById(Long agencyId) {
        return this.agencyRepository.getById(agencyId);
    }
}
