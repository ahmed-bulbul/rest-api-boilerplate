package com.restboilarplate.service.generic.impl;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;
import com.restboilarplate.repository.generic.GenericRepository;
import com.restboilarplate.service.generic.ServiceGeneric;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServiceGenericImpl<T extends BaseEntity> implements ServiceGeneric<T> {

    @Autowired
    protected GenericRepository<T> genericRepository;


    @Override
    public List<T> findAll() throws CustomException {
        return genericRepository.findAll();
    }

    @Override
    public T save(T entity) throws CustomException {
        return genericRepository.save(entity);
    }

    @Override
    public void delete(Long id) throws CustomException {
        genericRepository.deleteById(id);
    }

    @Override
    public Optional<T> findById(Long id) throws NotFoundException {
        return genericRepository.findById(id);
    }
}
