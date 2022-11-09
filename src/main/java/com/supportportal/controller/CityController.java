package com.supportportal.controller;

import com.supportportal.model.City;
import com.supportportal.service.impl.CityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {
    private CityServiceImpl cityService;

    public CityController(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        return new ResponseEntity<>(this.cityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable("id") Long cityId) {
        return new ResponseEntity<>(this.cityService.getById(cityId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> insert(@RequestBody City city) {
        return new ResponseEntity<>(this.cityService.insert(city), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable("id") Long cityId, @RequestBody City city) {
        return new ResponseEntity<>(this.cityService.update(cityId, city), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteById(@PathVariable Long id) {
        this.cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
