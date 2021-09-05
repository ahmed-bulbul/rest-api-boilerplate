package com.restboilarplate.service.generic.impl;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;
import com.restboilarplate.repository.generic.GenericRepository;
import com.restboilarplate.service.generic.ServiceGeneric;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class ServiceGenericImpl<T extends BaseEntity> implements ServiceGeneric<T> {

    @Autowired
    protected GenericRepository<T> genericRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        genericRepository.deleteById(id);
    }

    @Override
    public Optional<T> findById(Long id)  {
        return genericRepository.findById(id);
    }


    @Override
    public T update(T entity) throws CustomException {
        return genericRepository.save(entity);
    }



}
