package com.restboilarplate.repository.system;

import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.entity.system.SystemMenuAuthorization;
import com.restboilarplate.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMenuAuthorizationRepository extends GenericRepository<SystemMenuAuthorization> {
    SystemMenuAuthorization findBySystemMenu(SystemMenu menuInst_system);

    SystemMenuAuthorization findByUsernameAndSystemMenu(String username, SystemMenu systemMenu);
}
