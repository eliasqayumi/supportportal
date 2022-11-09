package com.supportportal.service;

import com.supportportal.model.Status;

import java.util.List;

public interface StatusService {
    List<Status> getAll();
    Status insert(Status agency);
    Status update(Integer agencyId,Status agency);
    void deleteById(Integer agencyId);
    Status getById(Integer agencyId);
}
