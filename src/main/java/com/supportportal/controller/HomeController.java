package com.supportportal.controller;

import com.supportportal.model.Home;
import com.supportportal.service.impl.HomeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
public class HomeController {
    private HomeServiceImpl homeService;

    public HomeController(HomeServiceImpl homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public ResponseEntity<List<Home>> getAll() {
        return new ResponseEntity<>(this.homeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Home> getById(@PathVariable("id") Long homeId) {
        return new ResponseEntity<>(this.homeService.getById(homeId), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Home>> getAllByUserId(@PathVariable("id") String userId) {
        return new ResponseEntity<>(this.homeService.getAllByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Home> insert(
            @RequestParam("price") String price, @RequestParam("neighbourhoodId") Long neighbourhoodId,
            @RequestParam("typeId") Long typeId, @RequestParam("roomNumberId") Integer roomNumberId,
            @RequestParam("currencyId") Long currencyId, @RequestParam("homeTypeId") Long homeTypeId,
            @RequestParam("userId") String userId, @RequestParam("floorNumber") Integer floorNumber,
            @RequestParam("totalFloor") Integer totalFloor, @RequestParam("address") String address,
            @RequestParam("details") String details, @RequestParam("statusId") Integer statusId,
            @RequestParam("header") String header
    ) {
        return new ResponseEntity<>(this.homeService.insert(
                price, neighbourhoodId, typeId, roomNumberId, currencyId, homeTypeId, userId, floorNumber, totalFloor
                , address, details, statusId,
        header), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Home> update(
            @PathVariable("id") Long homeId, @RequestParam("price") String price,
            @RequestParam("neighbourhoodId") Long neighbourhoodId, @RequestParam("typeId") Long typeId,
            @RequestParam("roomNumberId") Integer roomNumberId, @RequestParam("currencyId") Long currencyId,
            @RequestParam("homeTypeId") Long homeTypeId, @RequestParam("userId") String userId,
            @RequestParam("floorNumber") Integer floorNumber, @RequestParam("totalFloor") Integer totalFloor,
            @RequestParam("address") String address, @RequestParam("details") String details, @RequestParam("statusId") Integer statusId,   @RequestParam("header") String header) {
        return new ResponseEntity<>(
                this.homeService.update(
                        homeId, price, neighbourhoodId, typeId,
                        roomNumberId, currencyId, homeTypeId, userId,
                        floorNumber, totalFloor, address, details, statusId,header), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Home> deleteById(@PathVariable("id") Long homeId) {
        this.homeService.deleteById(homeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
