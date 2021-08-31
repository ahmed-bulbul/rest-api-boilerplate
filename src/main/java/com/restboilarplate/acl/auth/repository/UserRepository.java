package com.restboilarplate.acl.auth.repository;

import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.repository.generic.GenericRepository;

public interface UserRepository extends GenericRepository<User> {
    User findByUsername(String username);

    User getUserByUsername(String user);
}
