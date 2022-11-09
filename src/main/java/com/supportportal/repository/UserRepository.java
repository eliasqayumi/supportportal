package com.supportportal.repository;

import com.supportportal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
    User  findUserByUserId(String userId);
    User findUserByEmail(String email);
    User findUserByPassword(String currentPassword);
}
