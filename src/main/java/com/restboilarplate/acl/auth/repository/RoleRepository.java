package com.restboilarplate.acl.auth.repository;

import com.restboilarplate.acl.auth.entity.Role;
import com.restboilarplate.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role> {
    Role getRoleByRoleName(String role_user);
}
