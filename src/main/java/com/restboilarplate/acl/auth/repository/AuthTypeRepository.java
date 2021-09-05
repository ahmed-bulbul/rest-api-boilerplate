package com.restboilarplate.acl.auth.repository;

import com.restboilarplate.acl.auth.entity.settings.AuthType;
import com.restboilarplate.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTypeRepository extends GenericRepository<AuthType> {
    AuthType findByAuthType(String url_based);
}
