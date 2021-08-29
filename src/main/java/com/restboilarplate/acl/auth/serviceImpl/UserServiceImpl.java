package com.restboilarplate.acl.auth.serviceImpl;

import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.repository.RoleRepository;
import com.restboilarplate.acl.auth.repository.UserRepository;
import com.restboilarplate.acl.auth.service.UserService;
import com.restboilarplate.exception.AlreadyExistsException;
import com.restboilarplate.exception.NotFoundException;
import com.restboilarplate.service.generic.impl.ServiceGenericImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl extends ServiceGenericImpl<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;




    //creating user
    @Override
    public User createUser(User user, HttpServletRequest request)
            throws AlreadyExistsException, UnsupportedEncodingException {

        User local = this.userRepository.findByUsername(user.getUsername());
        if(local!=null){
            System.out.println("User is already exists");
            throw new AlreadyExistsException("User already exists");
        }else{


            String siteUrl =getSiteURL(request)+"/user";
            String randomCode = RandomString.make(64);

            local = this.userRepository.save(user);

        }

        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) throws NotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null){
            throw new NotFoundException("User Not Found ");

        }
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }


    /** User created By System Only
     * This function use only for System Auto creation User
     * ***[Don't be use for another purpose]
     * */
    @Override
    public User createUser(User user) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        if(local!=null){
            System.out.println("User is already exists");
            throw new AlreadyExistsException("User already exists");
        }else{


            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User updateUser(Long userId, User user) {
        User userDB = userRepository.findById(userId).get();

        if(Objects.nonNull(user.getFirstName()) &&
                !"".equalsIgnoreCase(user.getFirstName())){
            userDB.setFirstName(user.getFirstName());
        }

        if(Objects.nonNull(user.getLastName()) &&
                !"".equalsIgnoreCase(user.getLastName())){
            userDB.setLastName(user.getLastName());
        }

        if(Objects.nonNull(user.getPhone()) &&
                !"".equalsIgnoreCase(user.getPhone())){
            userDB.setPhone(user.getPhone());
        }
        //......filled all the field

        return userRepository.save(userDB);
    }






    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    /**
     * This function Returns the current url of the project
     * This function use only verification link that user is received
     * */
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }


}
