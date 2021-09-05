package com.restboilarplate.acl.security.filter;

import com.restboilarplate.acl.auth.entity.settings.AuthType;
import com.restboilarplate.acl.auth.repository.AuthTypeRepository;
import com.restboilarplate.service.system.SystemMenuAuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;

public class MyAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    private SystemMenuAuthService systemMenuAuthService;
    @Autowired
    private AuthTypeRepository authTypeRepository;

    @SneakyThrows
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {



        // For Role based
        AuthType authType = authTypeRepository.findByAuthType("ROLE_BASED");
        if (authType!=null){
            System.out.println("I am here... DecicionMgr............555");
            System.out.println(configAttributes);
            System.out.println(configAttributes);
            System.out.println("I am here... DecicionMgr............666");

            if (CollectionUtils.isEmpty(configAttributes)) {
                throw new AccessDeniedException("not allow");
            }
            Iterator<ConfigAttribute> ite = configAttributes.iterator();
            while (ite.hasNext()) {
                ConfigAttribute ca = ite.next();
                String needRole = ((org.springframework.security.access.SecurityConfig) ca).getAttribute();
                for (GrantedAuthority ga : authentication.getAuthorities()) {

                    System.out.println("Login user authority: ");
                    System.out.println(ga.getAuthority());
                    if(ga.getAuthority().equals(needRole)){
                        //匹配到有对应角色,则允许通过
                        return;
                    }
                }
            }

        }else{

            System.out.println("I am here... DecicionMgr............555");
            System.out.println(configAttributes);
            System.out.println(configAttributes);
            System.out.println("I am here... DecicionMgr............666");

            //for url based permission
            String reqUrl = String.valueOf(configAttributes.toArray()[0]);

            if (reqUrl.equals("EMPTY")){
                throw new AccessDeniedException("not allow");
            }

            if(systemMenuAuthService.isAuthorised(reqUrl)){
                return;
            }else{
                throw new AccessDeniedException("not allow");
            }

        }




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
