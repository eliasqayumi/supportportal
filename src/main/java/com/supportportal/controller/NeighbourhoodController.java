package com.supportportal.controller;

import com.supportportal.model.Neighbourhood;
import com.supportportal.service.impl.NeighbourhoodServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("neighbourhood")
public class NeighbourhoodController {
    private NeighbourhoodServiceImpl neighbourhoodService;

    public NeighbourhoodController(NeighbourhoodServiceImpl neighbourhoodService) {
        this.neighbourhoodService = neighbourhoodService;
    }

    @GetMapping
    public ResponseEntity<List<Neighbourhood>> getAll() {
        System.out.println("hello this is a problem");
        return new ResponseEntity<>(this.neighbourhoodService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Neighbourhood> getById(@PathVariable("id") Long neighbourhoodId) {
        return new ResponseEntity<>(this.neighbourhoodService.getById(neighbourhoodId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Neighbourhood> insert(@RequestBody Neighbourhood neighbourhood) {
        return new ResponseEntity<>(this.neighbourhoodService.insert(neighbourhood), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Neighbourhood> update(@PathVariable("id") Long neighbourhoodId, @RequestBody Neighbourhood neighbourhood) {
        return new ResponseEntity<>(this.neighbourhoodService.update(neighbourhoodId, neighbourhood), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Neighbourhood> deleteById(@PathVariable("id") Long neighbourhoodId) {
        this.neighbourhoodService.deleteById(neighbourhoodId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
