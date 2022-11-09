package com.supportportal.controller;

import com.supportportal.model.Status;
import com.supportportal.service.impl.StatusServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("status")
public class StatusController {
    private StatusServiceImpl statusService;

    public StatusController(StatusServiceImpl statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<Status>> getAll() {
        return new ResponseEntity<>(this.statusService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(@PathVariable("id") Integer statusId) {
        return new ResponseEntity<>(this.statusService.getById(statusId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Status>  insert(@RequestBody Status status) {
        return new ResponseEntity<>( this.statusService.insert(status), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status>  update(@PathVariable("id") Integer statusId, @RequestBody Status status) {
        return new ResponseEntity<>(this.statusService.update(statusId, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Status>  deleteById(@PathVariable("id") Integer statusId) {
        this.statusService.deleteById(statusId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
