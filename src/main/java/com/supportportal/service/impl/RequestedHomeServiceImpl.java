package com.supportportal.service.impl;

import com.supportportal.domain.User;
import com.supportportal.model.*;
import com.supportportal.repository.*;
import com.supportportal.service.RequestedHomeService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class RequestedHomeServiceImpl implements RequestedHomeService {
    private RequestedHomeRepository requestedHomeRepository;
    private PriorityRepository priorityRepository;
    private StatusRepository statusRepository;
    private RoomNumberRepository roomNumberRepository;
    private UserRepository userRepository;
    private CurrencyRepository currencyRepository;

    public RequestedHomeServiceImpl(RequestedHomeRepository requestedHomeRepository, PriorityRepository priorityRepository, StatusRepository statusRepository,
                                    RoomNumberRepository roomNumberRepository, UserRepository userRepository,CurrencyRepository currencyRepository
                                    ) {
        this.requestedHomeRepository = requestedHomeRepository;
        this.priorityRepository = priorityRepository;
        this.statusRepository = statusRepository;
        this.roomNumberRepository = roomNumberRepository;
        this.userRepository = userRepository;
        this.currencyRepository=currencyRepository;
    }

    @Override
    public List<RequestedHomes> getAll() {
        return this.requestedHomeRepository.findAll();
    }

    @Override
    public RequestedHomes insert(String recipientsName, String priorityId, String location, String budget,String currencyId,
                                 String roomNumberId, String statusId, String floors, String userId, String note) {
        Priority priority = this.priorityRepository.getById(Long.valueOf(priorityId));
        RoomNumber roomNumber = this.roomNumberRepository.getById(Integer.valueOf(roomNumberId));
        Status status = this.statusRepository.getById(Integer.valueOf(statusId));
        User user = this.userRepository.findUserByUserId(userId);
        Currency currency=this.currencyRepository.getById(Long.valueOf(currencyId));
        return this.requestedHomeRepository.save(
                new RequestedHomes(null, recipientsName, priority, location, budget,currency, roomNumber, status, user, floors, note));
    }

    @Override
    public RequestedHomes update(Long requestedHomeId,
                                 String recipientsName, String priorityId, String location, String budget,String currencyId,
                                 String roomNumberId, String statusId, String floors, String userId, String note
    ) {
        RequestedHomes currentRequestedHome = this.requestedHomeRepository.getById(requestedHomeId);
        currentRequestedHome.setRecipientsName(recipientsName);
        currentRequestedHome.setBudget(budget);
        currentRequestedHome.setCurrency(this.currencyRepository.getById(Long.valueOf(currencyId)));
        currentRequestedHome.setFloors(floors);
        currentRequestedHome.setPriority(this.priorityRepository.getById(Long.valueOf(priorityId)));
        currentRequestedHome.setLocation(location);
        currentRequestedHome.setStatus(this.statusRepository.getById(Integer.valueOf(statusId)));
        currentRequestedHome.setRoomNumber(this.roomNumberRepository.getById(Integer.valueOf(roomNumberId)));
        currentRequestedHome.setUser(this.userRepository.findUserByUserId(userId));
        currentRequestedHome.setNote(note);
        return this.requestedHomeRepository.save(currentRequestedHome);
    }

    @Override
    public void deleteById(Long requestedHomeId) {
        this.requestedHomeRepository.deleteById(requestedHomeId);
    }

    @Override
    public RequestedHomes getById(Long requestedHomesId) {
        return this.requestedHomeRepository.getById(requestedHomesId);
    }
}
