package com.restboilarplate.service.system;


import com.restboilarplate.acl.auth.entity.Role;
import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.repository.UserRepository;
import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.entity.system.SystemMenuAuthorization;
import com.restboilarplate.exception.NotFoundException;
import com.restboilarplate.repository.system.SystemMenuAuthorizationRepository;
import com.restboilarplate.repository.system.SystemMenuRepository;
import com.restboilarplate.util.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class SystemMenuAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SystemMenuRepository systemMenuRepository;

    @Autowired
    private SystemMenuAuthorizationRepository systemMenuAuthorizationRepository;

    String loginUser;
    User loginUserInst;

    public SystemMenuAuthService() throws NotFoundException {
         Boolean loggedIn = UserUtil.isLogged();
         if(loggedIn){
             System.out.println("LoogedIN");
         }else {
             System.out.println("Not logged in");
         }

    }
    public String checkLoggedIn(){
        Boolean loggedIn = UserUtil.isLogged();
        if(loggedIn){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("LoggedIN");
            return authentication.getName();

        }else {
            System.out.println("Not logged in");
        }
        return null;
    }
    public boolean checkSystemMenuAuth(String username, SystemMenu systemMenu) {
        SystemMenuAuthorization systemMenuAuthorization =
                this.systemMenuAuthorizationRepository.findByUsernameAndSystemMenu(username,systemMenu);
        if (systemMenuAuthorization!=null) {
            return true;
        }else{
            return false;
        }
    }

    public boolean isAuthorised(String reqUrl) throws NotFoundException {

        User user = this.userRepository.findByUsername(this.checkLoggedIn());
        Set<Role> roles = user.getRoles();
        SystemMenu systemMenu = this.systemMenuRepository.findSystemMenuByUrl(reqUrl);
        if (systemMenu!=null) {
            for (Role role : roles) {
                if (systemMenu.getAdminAccessOnly() && (role.getRoleName().equals("ROLE_ADMIN") || role.getRoleName().equals("ROLE_SUPER_ADMIN"))) {
                        return true;
                }
                else {
                    if(checkSystemMenuAuth(user.getUsername(),systemMenu)){
                        return true;
                    }
                }
            }
        }
        return false;
    }




}
