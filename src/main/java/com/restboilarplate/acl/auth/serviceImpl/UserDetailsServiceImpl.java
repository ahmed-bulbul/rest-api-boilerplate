package com.restboilarplate.acl.auth.serviceImpl;


import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    HttpSession session; //autowiring session

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=null;

        User userByUserName = this.userRepository.findByUsername(username);
        if(userByUserName!=null){
            user=userByUserName;
        }
//        User userByLoginCode = this.userRepository.findByLoginCode(username);
//        if (userByLoginCode!=null){
//            user=userByLoginCode;
//        }
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("No user found !!");
        }
       // session.setAttribute("loginCode",user.getLoginCode());
        return user;
    }
}
