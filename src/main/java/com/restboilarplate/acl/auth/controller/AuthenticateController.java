package com.restboilarplate.acl.auth.controller;


import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.entity.jwt.JwtRequest;
import com.restboilarplate.acl.auth.entity.jwt.JwtResponse;
import com.restboilarplate.acl.auth.serviceImpl.UserDetailsServiceImpl;
import com.restboilarplate.config.JwtUtils;
import com.restboilarplate.exception.InvalidCredentialsException;
import com.restboilarplate.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    HttpSession session; //autowiring session

    //generate token
    @PostMapping("/generateToken")
    public ResponseEntity<?> generateToken(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

        }catch (NotFoundException e){
            throw new Exception("User not found");
        }

        //authenticate
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        boolean validate = this.jwtUtils.validateToken(token,userDetails);
        System.out.println("Token is validate "+validate);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username,String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch (DisabledException e){
            throw new Exception("User Disabled"+ e.getMessage());
        }catch (BadCredentialsException e){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
    }


    //return the details of current user
    @GetMapping("/currentUser")
    public User getCurrentUser(Principal principal){
        return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
    }

}
