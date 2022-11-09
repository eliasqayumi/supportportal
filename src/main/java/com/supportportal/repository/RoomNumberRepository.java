package com.supportportal.repository;

import com.supportportal.model.RoomNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomNumberRepository extends JpaRepository<RoomNumber,Integer> {
    RoomNumber deleteRoomNumberById(Integer roomNumberId);
}
