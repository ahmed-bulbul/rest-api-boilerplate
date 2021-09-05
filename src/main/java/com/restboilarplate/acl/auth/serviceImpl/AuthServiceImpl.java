package com.restboilarplate.acl.auth.serviceImpl;

import com.restboilarplate.acl.auth.entity.settings.AuthType;
import com.restboilarplate.acl.auth.service.IAuthTypeService;
import com.restboilarplate.service.generic.impl.ServiceGenericImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl extends ServiceGenericImpl<AuthType> implements IAuthTypeService {
}
