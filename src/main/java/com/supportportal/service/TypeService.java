package com.supportportal.service;

import com.supportportal.model.Type;

import java.util.List;

public interface TypeService {
    List<Type> getAll();
    Type insert(Type type);
    Type update(Long typeId,Type type);
    void deleteById(Long typeId);
    Type getById(Long typeId);
}
