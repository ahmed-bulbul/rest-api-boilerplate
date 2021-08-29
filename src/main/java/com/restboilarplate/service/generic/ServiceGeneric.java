package com.restboilarplate.service.generic;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ServiceGeneric<T extends BaseEntity>  {

    List<T> findAll() throws CustomException;
    T save(T entity) throws CustomException;
    void delete(Long id) throws CustomException;
    Optional<T> findById(Long id) throws NotFoundException;
}

