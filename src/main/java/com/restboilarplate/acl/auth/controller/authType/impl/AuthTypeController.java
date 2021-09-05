package com.restboilarplate.acl.auth.controller.authType.impl;

import com.restboilarplate.acl.auth.controller.authType.IAuthTypeController;
import com.restboilarplate.acl.auth.entity.settings.AuthType;
import com.restboilarplate.controller.generic.impl.ControllerGenericImpl;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authType")
@CrossOrigin("*")
public class AuthTypeController extends ControllerGenericImpl<AuthType> implements IAuthTypeController {


    @Override
    public ResponseEntity<AuthType> findAll() throws CustomException {
        return super.findAll();
    }

    @Override
    public ResponseEntity<AuthType> findById(Long id) throws NotFoundException, CustomException {
        return super.findById(id);
    }

    @Override
    public ResponseEntity<String> delete(Long id) throws CustomException {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<Object> update(AuthType entity) throws CustomException {
        return super.update(entity);
    }
}
