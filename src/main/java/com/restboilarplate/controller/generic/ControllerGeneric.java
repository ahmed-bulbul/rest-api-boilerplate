package com.restboilarplate.controller.generic;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ControllerGeneric<T extends BaseEntity>  {

    ResponseEntity<Object> save(@RequestBody T entity) throws CustomException;

    ResponseEntity<T> findAll() throws CustomException;

    ResponseEntity<T> findById(@PathVariable Long id) throws NotFoundException, CustomException;

    ResponseEntity<String> delete(@PathVariable Long id) throws CustomException;

    ResponseEntity<Object>update(@RequestBody T entity) throws CustomException;

}
