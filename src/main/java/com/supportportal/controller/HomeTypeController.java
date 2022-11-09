package com.supportportal.controller;

import com.supportportal.model.HomeType;
import com.supportportal.service.impl.HomeTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home/type")
public class HomeTypeController {
    private HomeTypeServiceImpl homeTypeService;

    public HomeTypeController(HomeTypeServiceImpl homeTypeService) {
        this.homeTypeService = homeTypeService;
    }

    @GetMapping
    public ResponseEntity<List<HomeType>> getAll() {
        return new ResponseEntity<>( this.homeTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeType> getById(@PathVariable("id") Long homeTypeId) {
        return new ResponseEntity<>(  this.homeTypeService.getById(homeTypeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HomeType> insert(@RequestBody HomeType homeType) {
        return new ResponseEntity<>( this.homeTypeService.insert(homeType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HomeType> update(@PathVariable("id") Long homeTypeId, @RequestBody HomeType homeType) {
        return new ResponseEntity<>( this.homeTypeService.update(homeTypeId, homeType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HomeType> deleteById(@PathVariable("id") Long homeTypeId) {
        this.homeTypeService.deleteById(homeTypeId);
        return new ResponseEntity<>(  HttpStatus.OK);
    }
}
