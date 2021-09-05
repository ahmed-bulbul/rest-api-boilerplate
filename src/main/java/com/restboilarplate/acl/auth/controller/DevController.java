package com.restboilarplate.acl.auth.controller;

import com.restboilarplate.acl.auth.service.DevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/dev")
@CrossOrigin("*")

public class DevController {
    @Autowired
    private DevService devService;

    @GetMapping("/create")
    public void create() throws IOException {
        this.devService.create();
    }
}
