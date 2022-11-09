package com.supportportal.controller;

import com.supportportal.model.City;
import com.supportportal.model.Priority;
import com.supportportal.service.PriorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("priority")
public class PriorityController {
    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping
    public ResponseEntity<List<Priority>> getAll() {
        return new ResponseEntity<>(this.priorityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> getById(@PathVariable("id") Long priorityId) {
        return new ResponseEntity<>(this.priorityService.getById(priorityId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Priority> insert(@RequestBody Priority priority) {
        System.out.println(priority);
        return new ResponseEntity<>(this.priorityService.insert(priority), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Priority> update(@PathVariable("id") Long priorityId, @RequestBody Priority priority) {
        return new ResponseEntity<>(this.priorityService.update(priorityId, priority), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Priority> deleteById(@PathVariable Long id) {
        this.priorityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
