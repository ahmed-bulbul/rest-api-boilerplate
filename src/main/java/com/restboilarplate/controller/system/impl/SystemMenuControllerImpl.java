package com.restboilarplate.controller.system.impl;


import com.restboilarplate.controller.generic.impl.ControllerGenericImpl;
import com.restboilarplate.controller.system.SystemMenuController;
import com.restboilarplate.dto.System.SystemMenuAdminCoreUiDTO;
import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;
import com.restboilarplate.service.system.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin("*")
public class SystemMenuControllerImpl extends ControllerGenericImpl<SystemMenu> implements SystemMenuController{

    @Autowired
    private SystemMenuService systemMenuService;


    @GetMapping("/auth")
    public String auth(){
        return "Menu/auth =========Got it";
    }

    @Override
    public ResponseEntity<Object> save(SystemMenu entity) throws CustomException {
        return super.save(entity);
    }

    @Override
    public ResponseEntity<SystemMenu> findAll() throws CustomException {
        return super.findAll();
    }

    @Override
    public ResponseEntity<SystemMenu> findById(Long id) throws NotFoundException, CustomException {
        return super.findById(id);
    }

    @Override
    public ResponseEntity<String> delete(Long id) throws CustomException {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<Object> update(SystemMenu entity) throws CustomException {
        return super.update(entity);
    }

    @Override
    @GetMapping("/admincoreui")
    public List<SystemMenuAdminCoreUiDTO> getSystemMenuAdminCoreUiResponse() {
        return this.systemMenuService.getAdminCoreUiResponse();
    }
}
