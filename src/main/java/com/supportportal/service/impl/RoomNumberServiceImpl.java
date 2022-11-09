package com.supportportal.service.impl;

import com.supportportal.model.RoomNumber;
import com.supportportal.repository.RoomNumberRepository;
import com.supportportal.service.RoomNumberService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomNumberServiceImpl implements RoomNumberService {
    private RoomNumberRepository roomNumberRepository;

    public RoomNumberServiceImpl(RoomNumberRepository roomNumberRepository) {
        this.roomNumberRepository = roomNumberRepository;
    }

    @Override
    public List<RoomNumber> getAll() {
        return this.roomNumberRepository.findAll();
    }

    @Override
    public RoomNumber insert(RoomNumber roomNumber) {
        return this.roomNumberRepository.save(roomNumber);
    }

    @Override
    public RoomNumber update(Integer roomNumberId, RoomNumber roomNumber) {
        RoomNumber updateRoomNumber=this.roomNumberRepository.getById(roomNumberId);
        updateRoomNumber.setRoomNumber(roomNumber.getRoomNumber());
       return this.roomNumberRepository.save(updateRoomNumber);
    }

    @Override
    public void deleteById(Integer roomNumberId) {
         this.roomNumberRepository.deleteById(roomNumberId);
    }

    @Override
    public RoomNumber getById(Integer roomNumberId) {
        return this.roomNumberRepository.getById(roomNumberId);
    }
}
