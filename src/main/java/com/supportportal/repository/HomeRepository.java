package com.supportportal.repository;

import com.supportportal.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home,Long> {
    List<Home> findAllByUser_Id(Long userId);
}
