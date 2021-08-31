package com.restboilarplate.acl.security.filter;

import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.repository.system.SystemMenuRepository;
import com.restboilarplate.service.system.SystemMenuAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Collection;

public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SystemMenuAuthService systemMenuAuthService;
    @Autowired
    private SystemMenuRepository systemMenuRepository;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        /**if number found in url split it */
        url = url.replaceAll("[0-9]","");
        // TODO ignore url, please put it here for filtering and release
        if ( "/generateToken".equals(url) || "/currentUser".equals(url) || "/menu/getMenu/".equals(url)
                ||"/menu/parentMenu".equals(url)
                || "/menu/childMenu/".equals(url)){
            return null;
        }


        ArrayList<String> attributes;
        attributes = this.getAttributesByURL(url); //Here Goes Code


        System.out.println("I am dynamic url permission............chk-1");
        System.out.println("User Request URL:------------------" + url);
        System.out.println("Required attributes for access:----" + attributes);
        System.out.println("I am dynamic url permission............chk-2");

        return SecurityConfig.createList(String.valueOf(url));
    }

    private ArrayList<String> getAttributesByURL(String url) {
        ArrayList<String> attributes = new ArrayList<>();

        SystemMenu entityOptional= this.systemMenuRepository.findSystemMenuByUrl(url);
        if(entityOptional !=null){
            System.out.println("=======NOT NULLL==========");
            String configAttribute = entityOptional.getUrl();
            attributes.add(String.valueOf(configAttribute));

        }else {
            attributes.add("EMPTY");
        }

        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
