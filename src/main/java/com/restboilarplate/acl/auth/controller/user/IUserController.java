package com.restboilarplate.acl.auth.controller.user;

import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.entity.request.SignupRequest;
import com.restboilarplate.controller.generic.ControllerGeneric;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface IUserController extends ControllerGeneric<User> {
    public User createUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws Exception;
}
