package com.supportportal.service;

import com.supportportal.model.RoomNumber;

import java.util.List;

public interface RoomNumberService {
    List<RoomNumber> getAll();
    RoomNumber insert(RoomNumber roomNumber);
    RoomNumber update(Integer roomNumberId,RoomNumber roomNumber);
    void deleteById(Integer roomNumberId);
    RoomNumber getById(Integer roomNumberId);
}
