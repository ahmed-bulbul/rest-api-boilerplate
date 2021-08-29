package com.restboilarplate.acl.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class MyAccessDecisionManager implements AccessDecisionManager {
//
//    @Autowired
//    private CustomUserAuthorizationService customUserAuthorizationService;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        System.out.println("I am here... DecicionMgr............555");
        System.out.println(configAttributes);
        System.out.println(configAttributes);
        System.out.println("I am here... DecicionMgr............666");


        String reqUrl = String.valueOf(configAttributes.toArray()[0]);

        if (reqUrl.equals("EMPTY")){
            throw new AccessDeniedException("not allow");
        }


//        if (!customUserAuthorizationService.check(reqUrl)){
//            throw new AccessDeniedException("not allow");
//        }else {
//            return;
//        }

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
