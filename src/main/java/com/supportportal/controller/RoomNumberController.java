package com.supportportal.controller;

import com.supportportal.model.RoomNumber;
import com.supportportal.service.impl.RoomNumberServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("room/number")
public class RoomNumberController {
    private RoomNumberServiceImpl roomNumberService;

    public RoomNumberController(RoomNumberServiceImpl roomNumberService) {
        this.roomNumberService = roomNumberService;
    }

    @GetMapping
    public ResponseEntity<List<RoomNumber>> getAll() {
        return new ResponseEntity<>(this.roomNumberService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomNumber> getById(@PathVariable("id") Integer roomNumberId) {
        return new ResponseEntity<>(this.roomNumberService.getById(roomNumberId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomNumber>  insert(@RequestBody RoomNumber roomNumber) {
        return new ResponseEntity<>( this.roomNumberService.insert(roomNumber), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomNumber>  update(@PathVariable("id") Integer roomNumberId, @RequestBody RoomNumber roomNumber) {
        return new ResponseEntity<>(this.roomNumberService.update(roomNumberId, roomNumber), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoomNumber>  deleteById(@PathVariable("id") Integer roomNumberId) {
        this.roomNumberService.deleteById(roomNumberId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
