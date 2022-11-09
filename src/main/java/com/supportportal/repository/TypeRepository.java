package com.supportportal.repository;

import com.supportportal.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type deleteTypeById(Long typeId);
}
