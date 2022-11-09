package com.supportportal.service.impl;

import com.supportportal.model.Type;
import com.supportportal.repository.TypeRepository;
import com.supportportal.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    private TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> getAll() {
        return this.typeRepository.findAll();
    }

    @Override
    public Type insert(Type type) {
        return this.typeRepository.save(type);
    }

    @Override
    public Type update(Long typeId, Type type) {
        Type updateType= this.typeRepository.getById(typeId);
        updateType.setType(type.getType());
        return this.typeRepository.save(updateType);
    }

    @Override
    public void deleteById(Long typeId) {
        this.typeRepository.deleteById(typeId);
    }

    @Override
    public Type getById(Long typeId) {
        return this.typeRepository.getById(typeId);
    }
}
