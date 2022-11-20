package com.supportportal.service;

import com.supportportal.model.Currency;
import com.supportportal.model.RequestedHomes;

import java.util.List;

public interface RequestedHomeService {
    List<RequestedHomes> getAll();

    RequestedHomes insert(
            String recipientsName, String priorityId,
            String location, String budget, String currencyId,
            String roomNumberId, String statusId,
            String floors, String userId,
            String note);

    RequestedHomes update(Long requestedHomeId, String recipientsName, String priorityId,
                          String location, String budget, String currencyId,
                          String roomNumberId, String statusId,
                          String floors, String userId,
                          String note);
    void deleteById(Long requestedHomeId);

    RequestedHomes getById(Long requestedHomesId);
}
