package com.supportportal.service.impl;

import com.supportportal.model.Status;
import com.supportportal.repository.StatusRepository;
import com.supportportal.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> getAll() {
        return this.statusRepository.findAll();
    }

    @Override
    public Status insert(Status agency) {
        return this.statusRepository.save(agency);
    }

    @Override
    public Status update(Integer agencyId, Status agency) {
        Status updateStatus = this.statusRepository.getById(agencyId);
        updateStatus.setStatus(agency.getStatus());
       return this.statusRepository.save(updateStatus);
    }

    @Override
    public void deleteById(Integer agencyId) {
         this.statusRepository.deleteById(agencyId);
    }

    @Override
    public Status getById(Integer agencyId) {
        return this.statusRepository.getById(agencyId);
    }
}
