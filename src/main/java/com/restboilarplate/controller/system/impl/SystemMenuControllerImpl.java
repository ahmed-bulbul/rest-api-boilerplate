package com.restboilarplate.controller.system.impl;


import com.restboilarplate.controller.generic.impl.ControllerGenericImpl;
import com.restboilarplate.controller.system.SystemMenuController;
import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@CrossOrigin("*")
public class SystemMenuControllerImpl extends ControllerGenericImpl<SystemMenu> implements SystemMenuController{


    String prvString = "";
    public String chkGlobalAuth(String entityName){
        return "CR-D";
    }

    public SystemMenuControllerImpl() {
        String prvString = this.chkGlobalAuth("SystemMenu");
        System.out.println(prvString);
        this.prvString = prvString;
    }

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
        if(!this.prvString.contains("D")) {
            return ResponseEntity.ok("");
        }
        return super.delete(id);
    }

    @Override
    public ResponseEntity<Object> update(SystemMenu entity) throws CustomException {
        return super.update(entity);
    }
}
