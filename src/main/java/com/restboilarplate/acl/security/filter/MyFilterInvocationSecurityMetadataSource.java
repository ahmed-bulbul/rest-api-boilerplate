package com.restboilarplate.acl.security.filter;

import com.restboilarplate.acl.auth.entity.RequestUrl;
import com.restboilarplate.acl.auth.entity.settings.AuthType;
import com.restboilarplate.acl.auth.repository.AuthTypeRepository;
import com.restboilarplate.acl.auth.repository.RequestUrlRepository;
import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.repository.system.SystemMenuRepository;
import com.restboilarplate.service.system.SystemMenuAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SystemMenuAuthService systemMenuAuthService;
    @Autowired
    private SystemMenuRepository systemMenuRepository;
    @Autowired
    private AuthTypeRepository authTypeRepository;
    @Autowired
    private RequestUrlRepository requestUrlRepository;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        /**if number found in url split it */
        url = url.replaceAll("[0-9]","");
        // TODO ignore url, please put it here for filtering and release
        if ( "/generateToken".equals(url) || "/currentUser".equals(url) || "/menu/getMenu/".equals(url)
                || "/person/".equals(url)
                || "/dev/create".equals(url)
                || "/authType/create".equals(url)
                || "/menu/admincoreui".equals(url)
                || "/menu/query".equals(url)){
            return null;
        }

        AuthType authType = this.authTypeRepository.findByAuthType("URL_BASED");
        if (authType!=null){
            System.out.println("==========>>URL BASED PERMISSION>>=======");
            ArrayList<String> attributes;
            attributes = this.getAttributesByURL(url); //Here Goes Code
            System.out.println("I am dynamic url permission............chk-1");
            System.out.println("User Request URL:------------------" + url);
            System.out.println("Required attributes for access:----" + attributes);
            System.out.println("I am dynamic url permission............chk-2");
            return SecurityConfig.createList(String.valueOf(url));
        }else {
            System.out.println("==========>>ROLE  BASED PERMISSION>>=======");
            ArrayList<String> attributes;
            attributes = this.getAttributesByURL2(url); //Here Goes Code
            System.out.println("I am dynamic url permission............chk-1");
            System.out.println("User Request URL:------------------" + url);
            System.out.println("Required attributes for access:----" + attributes);
            System.out.println("I am dynamic url permission............chk-2");
            return SecurityConfig.createList(attributes.toArray(new String[attributes.size()]));
        }

    }

    private ArrayList<String> getAttributesByURL(String url) {
        ArrayList<String> attributes = new ArrayList<>();

//        String reqUrl = url;
//        String[] urlArray = url.split("/");
//        String firstPath = urlArray[1];

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
    public ArrayList<String> getAttributesByURL2(String url){

        ArrayList<String> attributes = new ArrayList<>();
        Optional<RequestUrl> entityOptional= this.requestUrlRepository.getByUrl(url);
        if(entityOptional.isPresent()){
            RequestUrl requestUrl = entityOptional.get();
            String configAttribute = requestUrl.getConfigAttribute();
            if(configAttribute != null && !configAttribute.equals("")){
                ArrayList<String> elephantList = new ArrayList<>(Arrays.asList(configAttribute.split(",")));
                for (String thisAttribute : elephantList) {
                    System.out.println(thisAttribute);
                    attributes.add(thisAttribute.trim());
                }
            } else {
                attributes.add("ROLE_USER");
            }
        } else {
            attributes.add("ROLE_USER");
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
