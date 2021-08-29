package com.restboilarplate.acl.auth.controller.user.impl;

import com.restboilarplate.acl.auth.controller.user.UserController;
import com.restboilarplate.acl.auth.entity.Role;
import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.entity.request.SignupRequest;
import com.restboilarplate.acl.auth.repository.RoleRepository;
import com.restboilarplate.acl.auth.service.UserService;
import com.restboilarplate.controller.generic.impl.ControllerGenericImpl;
import com.restboilarplate.exception.CustomException;
import com.restboilarplate.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserControllerImpl extends ControllerGenericImpl<User> implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private RoleRepository roleRepository;


    @Override
    public ResponseEntity<User> findById(Long id) throws NotFoundException, CustomException {
        return super.findById(id);
    }

    @Override
    public ResponseEntity<String> delete(Long id) throws CustomException {
        return super.delete(id);
    }

    @Override
    @PostMapping("/register")
    public User createUser(SignupRequest signUpRequest, HttpServletRequest request) throws Exception {
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getPhone(),
                this.bCryptPasswordEncoder.encode(signUpRequest.getPassword()));


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.getRoleByRoleName("ROLE_USER");
            if (userRole==null){
                throw new NotFoundException("Role not found");
            }
            roles.add(userRole);
        }else{
            strRoles.forEach(role -> {
                if ("super_admin".equals(role)) {
                    Role superAdminRole = roleRepository.getRoleByRoleName("ROLE_SUPER_ADMIN");
                    if (superAdminRole == null) {
                        try {
                            throw new NotFoundException("Role not found");
                        } catch (NotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    roles.add(superAdminRole);
                } else if ("admin".equals(role)) {
                    Role adminRole = roleRepository.getRoleByRoleName("ROLE_ADMIN");
                    if (adminRole == null) {
                        try {
                            throw new NotFoundException("Role not found");
                        } catch (NotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.getRoleByRoleName("ROLE_USER");
                    if (userRole == null) {
                        try {
                            throw new NotFoundException("Role not found");
                        } catch (NotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    roles.add(userRole);
                }
            });
        }
        //for dev purpose verification and enabled by default true
       // user.setVerificationCode(null);
        user.setEnabled(true);
        user.setRoles(roles);
        return this.userService.createUser(user,request);
    }
}
