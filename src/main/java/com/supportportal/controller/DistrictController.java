package com.supportportal.controller;

import com.supportportal.model.District;
import com.supportportal.service.impl.DistrictServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictController {
    private DistrictServiceImpl districtService;

    public DistrictController(DistrictServiceImpl districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public ResponseEntity<List<District>> getAll() {
        return new ResponseEntity<>(this.districtService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<District>  getById(@PathVariable("id") Long districtId) {
        return new ResponseEntity<>(this.districtService.getById(districtId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<District> insert(@RequestBody District district) {
        return new ResponseEntity<>(this.districtService.insert(district), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<District> update(@PathVariable("id") Long districtId, @RequestBody District district) {
        return new ResponseEntity<>(this.districtService.update(districtId, district),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<District> deleteById(@PathVariable("id") Long districtId) {
        this.districtService.deleteById(districtId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
