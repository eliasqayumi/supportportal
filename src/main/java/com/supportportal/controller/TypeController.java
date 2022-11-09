package com.supportportal.controller;

import com.supportportal.model.Type;
import com.supportportal.service.impl.TypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {
    private TypeServiceImpl typeService;

    public TypeController(TypeServiceImpl typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<Type>> getAll() {
        return new ResponseEntity<>(this.typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getById(@PathVariable("id") Long typeId) {
        return new ResponseEntity<>(this.typeService.getById(typeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type> insert(@RequestBody Type type) {
        return new ResponseEntity<>(this.typeService.insert(type), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> update(@PathVariable("id") Long typeId, @RequestBody Type type) {
        return new ResponseEntity<>(this.typeService.update(typeId, type), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Type> deleteById(@PathVariable("id") Long typeId) {
        this.typeService.deleteById(typeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
