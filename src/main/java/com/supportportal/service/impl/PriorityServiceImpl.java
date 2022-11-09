package com.supportportal.service.impl;

import com.supportportal.model.Priority;
import com.supportportal.repository.PriorityRepository;
import com.supportportal.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    private PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public List<Priority> getAll() {
        return this.priorityRepository.findAll();
    }

    @Override
    public Priority insert(Priority priority) {
        return this.priorityRepository.save(priority);
    }

    @Override
    public Priority update(Long id, Priority priority) {
        Priority currentPriority=this.priorityRepository.getById(id);
        currentPriority.setPriority(priority.getPriority());
        return this.priorityRepository.save(currentPriority);
    }

    @Override
    public void deleteById(Long id) {
        this. priorityRepository.deleteById(id);
    }

    @Override
    public Priority getById(Long id) {
        return this.priorityRepository.getById(id);
    }
}
