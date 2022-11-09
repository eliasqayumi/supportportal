package com.supportportal.controller;

import com.supportportal.model.RequestedHomes;
import com.supportportal.service.impl.RequestedHomeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("requested-homes")
public class RequestedHomesController {
    private RequestedHomeServiceImpl RequestedHomesService;

    public RequestedHomesController(RequestedHomeServiceImpl RequestedHomesService) {
        this.RequestedHomesService = RequestedHomesService;
    }

    @GetMapping
    public ResponseEntity<List<RequestedHomes>> getAll() {
        return new ResponseEntity<>(this.RequestedHomesService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestedHomes> getById(@PathVariable("id") Long requestedHomesId) {
        return new ResponseEntity<>(this.RequestedHomesService.getById(requestedHomesId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RequestedHomes> insert(
            @RequestParam("recipientsName") String recipientsName, @RequestParam("priorityId") String priorityId,
            @RequestParam("location") String location, @RequestParam("budget") String budget,
            @RequestParam("roomNumberId") String roomNumberId, @RequestParam("statusId") String statusId,
            @RequestParam("floors") String floors, @RequestParam("userId") String userId,
            @RequestParam("note") String note
    ) {
        System.out.println(recipientsName+"********"+priorityId+"********"+location+"********"+budget+"********"+roomNumberId+"********"+statusId+"********"+floors+"********"+userId+"********"+note);
        return new ResponseEntity<>(this.RequestedHomesService.insert(recipientsName, priorityId, location, budget, roomNumberId, statusId, floors, userId, note), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestedHomes> update(
            @PathVariable("id") Long requestedHomesId, @RequestParam("recipientsName") String recipientsName,
            @RequestParam("priorityId") String priorityId, @RequestParam("location") String location,
            @RequestParam("budget") String budget, @RequestParam("roomNumberId") String roomNumberId,
            @RequestParam("statusId") String statusId, @RequestParam("floors") String floors,
            @RequestParam("userId") String userId, @RequestParam("note") String note
    ) {
        System.out.println(recipientsName + " **** " + priorityId + " **** " + location + " **** " + budget + " **** " + roomNumberId + " **** " + statusId + " **** " + floors + " **** " + userId + "****" + note);
        return new ResponseEntity<>(this.RequestedHomesService.update(requestedHomesId, recipientsName, priorityId, location, budget, roomNumberId, statusId, floors, userId, note), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RequestedHomes> deleteById(@PathVariable Long id) {
        System.out.println("**************************" + id);
        this.RequestedHomesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}