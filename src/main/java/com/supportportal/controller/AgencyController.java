package com.supportportal.controller;

import com.supportportal.exception.ExceptionHandling;
import com.supportportal.model.Agency;
import com.supportportal.service.impl.AgencyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/user/agency", "/agency"})
public class AgencyController extends ExceptionHandling {
    private AgencyServiceImpl agencyService;

    public AgencyController(AgencyServiceImpl agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping
    public ResponseEntity<List<Agency>> getAll() {
        return new ResponseEntity<>(this.agencyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agency> getById(@PathVariable("id") Long agencyId) {
        return new ResponseEntity<>( this.agencyService.getById(agencyId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Agency> insert(@RequestBody Agency agency) {
        return new ResponseEntity<>(this.agencyService.insert(agency), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agency> update(@PathVariable("id") Long agencyId, @RequestBody Agency agency) {
        return new ResponseEntity<>(this.agencyService.update(agencyId, agency), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Agency> deleteById(@PathVariable("id") Long agencyId) {
        this.agencyService.deleteById(agencyId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
