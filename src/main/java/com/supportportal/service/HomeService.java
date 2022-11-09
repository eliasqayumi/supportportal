package com.supportportal.service;

import com.supportportal.model.Home;

import java.util.List;

public interface HomeService {
    List<Home> getAll();
    Home insert(Long price,Long neighbourhoodId,Long typeId,Integer roomNumberId,Long currencyId,Long homeTypeId,String userId,Integer floorNumber,Integer totalFloor,String address,String details,Integer statusId,String header);
    Home update(Long id,Long price, Long neighbourhoodId, Long typeId, Integer roomNumberId, Long currencyId, Long homeTypeId, String userId, Integer floorNumber, Integer totalFloor, String address, String details, Integer statusId,String header);
    void deleteById(Long homeId);
    Home getById(Long homeId);
    List<Home> getAllByUserId(String userId);
}
