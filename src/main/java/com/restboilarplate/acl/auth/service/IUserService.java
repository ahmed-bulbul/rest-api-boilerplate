package com.restboilarplate.acl.auth.service;

import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.exception.NotFoundException;
import com.restboilarplate.service.generic.ServiceGeneric;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService extends ServiceGeneric<User> {

    //creating user
    public User createUser(User user, HttpServletRequest request) throws Exception;

    //get user by username
    public User getUser(String username) throws NotFoundException;

    List<User> fetchUserList();

    //delete user by id
    public void deleteUser(Long userId);

    //update user by id
    User updateUser(Long userId, User user);


    /** User created By System Only
     * This function use only for System Auto creation User
     * ***[Don't be use for another purpose]
     * Its a danger if you are use
     * */
    public User createUser(User user) throws Exception;


    /** reset password */
//    User resetPassword(ResetPasswordRequest resetPasswordRequest, Long id)
//            throws UsernameOrPasswordNotMatchedException, UserNotFoundException, SimilarPasswordException;


    //User findById(Long id) throws UserNotFoundException;

    User update(User user);
}
