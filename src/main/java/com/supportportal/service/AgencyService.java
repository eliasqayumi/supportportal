package com.supportportal.service;

import com.supportportal.model.Agency;

import java.util.List;

public interface AgencyService {
    List<Agency> getAll();
    Agency insert(Agency agency);
    Agency update(Long agencyId,Agency agency);
    void deleteById(Long agencyId);
    Agency getById(Long agencyId);
}
