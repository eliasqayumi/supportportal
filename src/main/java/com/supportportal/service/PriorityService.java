package com.supportportal.service;

import com.supportportal.model.Priority;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PriorityService {
    List<Priority> getAll();
    Priority insert(Priority priority);
    Priority update (Long id,Priority priority);
    void deleteById(Long id);
    Priority getById(Long id);
}
