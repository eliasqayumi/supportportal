package com.supportportal.service.impl;

import com.supportportal.domain.User;
import com.supportportal.model.*;
import com.supportportal.repository.*;
import com.supportportal.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    private HomeRepository homeRepository;
    private UserRepository userRepository;
    private NeighbourhoodRepository neighbourhoodRepository;
    private TypeRepository typeRepository;
    private RoomNumberRepository roomNumberRepository;
    private CurrencyRepository currencyRepository;
    private HomeTypeRepository homeTypeRepository;
    private StatusRepository statusRepository;

    @Autowired
    public HomeServiceImpl(HomeRepository homeRepository, UserRepository userRepository,
                           NeighbourhoodRepository neighbourhoodRepository, TypeRepository typeRepository,
                           RoomNumberRepository roomNumberRepository, CurrencyRepository currencyRepository,
                           HomeTypeRepository homeTypeRepository, StatusRepository statusRepository) {
        this.homeRepository = homeRepository;
        this.userRepository = userRepository;
        this.neighbourhoodRepository = neighbourhoodRepository;
        this.typeRepository = typeRepository;
        this.roomNumberRepository = roomNumberRepository;
        this.currencyRepository = currencyRepository;
        this.homeTypeRepository = homeTypeRepository;
        this.statusRepository = statusRepository;
    }

    public HomeServiceImpl(HomeRepository homeRepository, UserRepository userRepository) {
        this.homeRepository = homeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Home> getAll() {
        return this.homeRepository.findAll();
    }

    @Override
    public Home insert(Long price, Long neighbourhoodId, Long typeId, Integer roomNumberId, Long currencyId, Long homeTypeId, String userId, Integer floorNumber, Integer totalFloor, String address, String details, Integer statusId,String header) {
        return this.homeRepository.save(createHome(null,price,neighbourhoodId,typeId,roomNumberId,currencyId,homeTypeId,userId,floorNumber,totalFloor,address,details,statusId,header));
    }


    @Override
    public Home update(Long id,Long price, Long neighbourhoodId, Long typeId, Integer roomNumberId, Long currencyId, Long homeTypeId, String userId, Integer floorNumber, Integer totalFloor, String address, String details, Integer statusId,String header) {
        Home currentHome=this.homeRepository.getById(id);
        Home home=createHome(id,price,neighbourhoodId,typeId,roomNumberId,currencyId,homeTypeId,userId,floorNumber,totalFloor,address,details,statusId,header);
        currentHome.setPrice(home.getPrice());
        currentHome.setNeighbourhood(home.getNeighbourhood());
        currentHome.setType(home.getType());
        currentHome.setRoomNumber(home.getRoomNumber());
        currentHome.setCurrency(home.getCurrency());
        currentHome.setHomeType(home.getHomeType());
        currentHome.setUser(home.getUser());
        currentHome.setFloorNumber(home.getFloorNumber());
        currentHome.setTotalFloor(home.getTotalFloor());
        currentHome.setAddress(home.getAddress());
        currentHome.setDetails(home.getDetails());
        currentHome.setStatus(home.getStatus());
        currentHome.setHeader(home.getHeader());
        return this.homeRepository.save(currentHome);
    }

    @Override
    public void deleteById(Long homeId) {
        this.homeRepository.deleteById(homeId);
    }

    @Override
    public Home getById(Long homeId) {
        return this.homeRepository.getById(homeId);
    }

    @Override
    public List<Home> getAllByUserId(String userId) {
        User user = this.userRepository.findUserByUserId(userId);
        return this.homeRepository.findAllByUser_Id(user.getId());
    }
    private Home createHome(Long id,Long price, Long neighbourhoodId, Long typeId, Integer roomNumberId, Long currencyId, Long homeTypeId, String userId, Integer floorNumber, Integer totalFloor, String address, String details, Integer statusId,String header){
        Neighbourhood neighbourhood = this.neighbourhoodRepository.getById(neighbourhoodId);
        Type type = this.typeRepository.getById(typeId);
        RoomNumber roomNumber = this.roomNumberRepository.getById(roomNumberId);
        Currency currency = this.currencyRepository.getById(currencyId);
        HomeType homeType = this.homeTypeRepository.getById(homeTypeId);
        User user = this.userRepository.findUserByUserId(userId);
        Status status = this.statusRepository.getById(statusId);
        return new Home(id, price, neighbourhood, type, roomNumber, currency, homeType, user, floorNumber, totalFloor, address,header, details, status);
    }
}
