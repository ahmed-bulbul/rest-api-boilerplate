package com.restboilarplate;

import com.restboilarplate.acl.auth.entity.RequestUrl;
import com.restboilarplate.acl.auth.entity.Role;
import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.repository.RequestUrlRepository;
import com.restboilarplate.acl.auth.repository.RoleRepository;
import com.restboilarplate.acl.auth.repository.UserRepository;
import com.restboilarplate.acl.auth.service.UserService;
import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.entity.system.SystemMenuAuthorization;
import com.restboilarplate.repository.system.SystemMenuAuthorizationRepository;
import com.restboilarplate.repository.system.SystemMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SystemMenuRepository systemMenuRepository;
    @Autowired
    private RequestUrlRepository requestUrlRepository;

    @Autowired
    private SystemMenuAuthorizationRepository systemMenuAuthorizationRepository;


    @Autowired
    private HttpServletRequest request;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**Create Basic roles*/
    public void createAppBasicRoles(){

        Role chkRoleExists = this.roleRepository.getRoleByRoleName("ROLE_SUPER_ADMIN");

        if(chkRoleExists==null){
            Role roleInst1 = new Role("ROLE_SUPER_ADMIN","");
            this.roleRepository.save(roleInst1);
        }

        chkRoleExists = this.roleRepository.getRoleByRoleName("ROLE_ADMIN");
        if(chkRoleExists==null){
            Role roleInst2 = new Role("ROLE_ADMIN","");
            this.roleRepository.save(roleInst2);
        }

        chkRoleExists = this.roleRepository.getRoleByRoleName("ROLE_USER");
        if(chkRoleExists==null){
            Role roleInst3= new Role("ROLE_USER","");
            this.roleRepository.save(roleInst3);
        }
    }

    /**Create Basic Users*/
        public void createAppBasicUsers() throws Exception {

        Role roleSuperAdmin=this.roleRepository.getRoleByRoleName("ROLE_SUPER_ADMIN");
        Role roleAdmin=this.roleRepository.getRoleByRoleName("ROLE_ADMIN");
        Role roleUser=this.roleRepository.getRoleByRoleName("ROLE_USER");


        /**
         * create super admin by system auto creation
         * */
        User superAdmin = this.userRepository.findByUsername("bulbul");

        Set<Role> rolesSuperAdminSet = new HashSet<>();
        rolesSuperAdminSet.add(roleSuperAdmin);
        rolesSuperAdminSet.add(roleAdmin);

        if(superAdmin==null){

            superAdmin=new User();

            superAdmin.setFirstName("Bulbul");
            superAdmin.setLastName("Ahmed");
            superAdmin.setUsername("bulbul");
            superAdmin.setPassword(this.bCryptPasswordEncoder.encode("1234"));
            superAdmin.setEmail("bulbul@gmail.com");
            superAdmin.setPhone("01753155400");
         //   superAdmin.setProfile("default.png");
            superAdmin.setEnabled(true);
//            superAdmin.setVerificationCode(null);
//            superAdmin.setCreatedBy("SYSTEM");

            superAdmin.setRoles(rolesSuperAdminSet);

            superAdmin= this.userService.createUser(superAdmin);
            System.out.println("=========Super Admin Created========== "+superAdmin.getUsername());
        }
        /**
         * create admin by system auto creation
         * */
        User admin = this.userRepository.findByUsername("siam");
        Set<Role> rolesAdminSet = new HashSet<>();
        rolesAdminSet.add(roleAdmin);

        if(admin==null){

            admin=new User();

            admin.setFirstName("Siam");
            admin.setLastName("Ahmed");
            admin.setUsername("siam");
            admin.setPassword(this.bCryptPasswordEncoder.encode("1234"));
            admin.setEmail("siam@gmail.com");
            admin.setPhone("0215864");
//            admin.setProfile("default.png");
//            admin.setCreatedBy("SYSTEM");
            admin.setEnabled(true);
       //     admin.setVerificationCode(null);
            admin.setRoles(rolesAdminSet);

            /** Also Super Admin */
            admin.setRoles(rolesSuperAdminSet);

            admin=this.userService.createUser(admin);
            System.out.println("==========Admin Created========== "+admin.getUsername());

        }

        /**
         * create user by system auto creation
         * */
        User user = this.userRepository.findByUsername("system");
        Set<Role> rolesUserSet = new HashSet<>();
        rolesUserSet.add(roleUser);

        if(user==null){

            user=new User();

            user.setFirstName("Sakib");
            user.setLastName("Al Hasan");
            user.setUsername("system");
            user.setPassword(this.bCryptPasswordEncoder.encode("1234"));
            user.setEmail("sakib@gmail.com");
            user.setPhone("88995566");
//            user.setProfile("default.png");
//            user.setCreatedBy("SYSTEM");
            user.setEnabled(true);
      //      user.setVerificationCode(null);
            user.setRoles(rolesUserSet);

            user=this.userService.createUser(user);
            System.out.println("===============User Created============= "+user.getUsername());
        }
    }

    /***System Menu Creating============*/
    public void createSystemMenu(){

        // SYSTEM  Generate // -----------------------------------------------------------------------------------------
        SystemMenu menuInst_System;
        boolean exist = systemMenuRepository.existsByCode("SYSTEM");
        if(!exist){
            menuInst_System = new SystemMenu("SYSTEM", "System", "/systemRootMenu", "<i class=\"nav-icon fab fa-windows\"></i>", true, 100);
            menuInst_System = systemMenuRepository.save(menuInst_System);
        } else {
            menuInst_System = systemMenuRepository.findByCode("SYSTEM");
        }

        // Set to Request URL Map
        RequestUrl requestUrlInst = requestUrlRepository.getByUrlPath("/systemRootMenu#");
        if(requestUrlInst == null){
            requestUrlInst = new RequestUrl("/systemRootMenu#", "ROLE_USER", null, new Date(), "SYSTEM");
            requestUrlRepository.save(requestUrlInst);
        }



        SystemMenu menuInst_User = systemMenuRepository.getByCode("AUTH_USER");
        if(menuInst_User == null){
            SystemMenu menuInst_user = new SystemMenu("AUTH_USER", "User", "/user/index", "", true, 110);
            menuInst_user = systemMenuRepository.save(menuInst_user);
            menuInst_user.setParentMenu(menuInst_System);
            systemMenuRepository.save(menuInst_user);
        }
        // Set to Request URL Map
        requestUrlInst = requestUrlRepository.getByUrlPath("/user/index");
        if(requestUrlInst == null){
            requestUrlInst = new RequestUrl("/user/index", "ROLE_USER", null, new Date(), "SYSTEM");
            requestUrlRepository.save(requestUrlInst);
        }



        SystemMenu menuInst_Role = systemMenuRepository.getByCode("AUTH_ROLE");
        if(menuInst_Role == null){
            SystemMenu menuInst_role= new SystemMenu("AUTH_ROLE", "Role", "/role/index", "", true, 120);
            menuInst_role = systemMenuRepository.save(menuInst_role);
            menuInst_role.setParentMenu(menuInst_System);
            systemMenuRepository.save(menuInst_role);
        }
        // Set to Request URL Map
        requestUrlInst = requestUrlRepository.getByUrlPath("/role/index");
        if(requestUrlInst == null){
            requestUrlInst = new RequestUrl("/role/index", "ROLE_USER", null, new Date(), "SYSTEM");
            requestUrlRepository.save(requestUrlInst);
        }


        SystemMenu menuInst_UserRoleChk = systemMenuRepository.getByCode("AUTH_USER_ROLE");
        if(menuInst_UserRoleChk == null){
            SystemMenu menuInst_UserRole= new SystemMenu("AUTH_USER_ROLE", "User Role", "/userrole/index", "", true, 130);
            menuInst_UserRole = systemMenuRepository.save(menuInst_UserRole);
            menuInst_UserRole.setParentMenu(menuInst_System);
            systemMenuRepository.save(menuInst_UserRole);
        }
        // Set to Request URL Map
        requestUrlInst = requestUrlRepository.getByUrlPath("/userrole/index");
        if(requestUrlInst == null){
            requestUrlInst = new RequestUrl("/userrole/index", "ROLE_USER", null, new Date(), "SYSTEM");
            requestUrlRepository.save(requestUrlInst);
        }


        SystemMenu menuInst_RequestMapChk = systemMenuRepository.getByCode("AUTH_REQUEST_MAP");
        if(menuInst_RequestMapChk == null){
            SystemMenu menuInst_RequestMap= new SystemMenu("AUTH_REQUEST_MAP", "Request Map", "/requesturl/index", "", true, 140);
            menuInst_RequestMap = systemMenuRepository.save(menuInst_RequestMap);
            menuInst_RequestMap.setParentMenu(menuInst_System);
            systemMenuRepository.save(menuInst_RequestMap);
        }
        // Set to Request URL Map
        requestUrlInst = requestUrlRepository.getByUrlPath("/requesturl/index");
        if(requestUrlInst == null){
            requestUrlInst = new RequestUrl("/requesturl/index", "ROLE_USER", null, new Date(), "SYSTEM");
            requestUrlRepository.save(requestUrlInst);
        }


        SystemMenu menuInst_MenuDefChk = systemMenuRepository.getByCode("SYS_MENU_DEF");
        if(menuInst_MenuDefChk == null){
            SystemMenu menuInst_MenuDef= new SystemMenu("SYS_MENU_DEF", "Menu Definition", "/menu/index", "", true, 150);
            menuInst_MenuDef = systemMenuRepository.save(menuInst_MenuDef);
            menuInst_MenuDef.setParentMenu(menuInst_System);
            systemMenuRepository.save(menuInst_MenuDef);
        }
        // Set to Request URL Map
        requestUrlInst = requestUrlRepository.getByUrlPath("/menu/index");
        if(requestUrlInst == null){
            requestUrlInst = new RequestUrl("/menu/index", "ROLE_USER", null, new Date(), "SYSTEM");
            requestUrlRepository.save(requestUrlInst);
        }



        SystemMenu menuInst_sysMenuAuthorizationChk = systemMenuRepository.getByCode("SYS_MENU_AUTH");
        if (menuInst_sysMenuAuthorizationChk==null){
            SystemMenu menuInst_sysMenuAuthorization= new SystemMenu("SYS_MENU_AUTH", "Menu Authorization", "/menu/auth", "", true, 155);
            menuInst_sysMenuAuthorization = systemMenuRepository.save(menuInst_sysMenuAuthorization);
            menuInst_sysMenuAuthorization.setParentMenu(menuInst_System);
            systemMenuRepository.save(menuInst_sysMenuAuthorization);
        }

        //Set to Request URL Map
        requestUrlInst = requestUrlRepository.getByUrlPath("/menu/auth");
        if(requestUrlInst == null){
            requestUrlInst = new RequestUrl("/menu/auth", "ROLE_USER", null, new Date(), "SYSTEM");
            requestUrlRepository.save(requestUrlInst);
        }
    }

    /** System Menu authorization*/
    public  void createSystemMenuAuthorization(){
        /**System menu authorization*/
        SystemMenu menuInst_System = systemMenuRepository.findByCode("SYSTEM");
        SystemMenuAuthorization systemMenuAuthorizationInst = this.systemMenuAuthorizationRepository.findBySystemMenu(menuInst_System);
        if(systemMenuAuthorizationInst==null){
            User userGeneral = this.userRepository.getUserByUsername("system");
            systemMenuAuthorizationInst = new SystemMenuAuthorization("SYSTEM",null,userGeneral.getUsername(),menuInst_System,true,101,true,true,true);
            systemMenuAuthorizationRepository.save(systemMenuAuthorizationInst);
        }
        //Set to Auth user  SystemMenuAuthorization
        SystemMenu menuInst_User = systemMenuRepository.getByCode("AUTH_USER");
        systemMenuAuthorizationInst = this.systemMenuAuthorizationRepository.findBySystemMenu(menuInst_User);
        if(systemMenuAuthorizationInst==null){
            User userGeneral = this.userRepository.getUserByUsername("system");
            systemMenuAuthorizationInst = new SystemMenuAuthorization("AUTH_USER",null,userGeneral.getUsername(),menuInst_User,true,102,true,true,true);
            systemMenuAuthorizationRepository.save(systemMenuAuthorizationInst);
        }
        //Set to SystemMenuAuthorization
        SystemMenu menuInst_Role = systemMenuRepository.getByCode("AUTH_ROLE");
        systemMenuAuthorizationInst = this.systemMenuAuthorizationRepository.findBySystemMenu(menuInst_Role);
        if(systemMenuAuthorizationInst==null){
            User userGeneral = this.userRepository.getUserByUsername("system");
            systemMenuAuthorizationInst = new SystemMenuAuthorization("AUTH_ROLE",null,userGeneral.getUsername(),menuInst_Role,true,103,true,true,true);
            systemMenuAuthorizationRepository.save(systemMenuAuthorizationInst);
        }
        //Set to SystemMenuAuthorization
        SystemMenu menuInst_UserRoleChk = systemMenuRepository.getByCode("AUTH_USER_ROLE");
        systemMenuAuthorizationInst = this.systemMenuAuthorizationRepository.findBySystemMenu(menuInst_UserRoleChk);
        if(systemMenuAuthorizationInst==null){
            User userGeneral = this.userRepository.getUserByUsername("system");
            systemMenuAuthorizationInst = new SystemMenuAuthorization("AUTH_USER_ROLE",null,userGeneral.getUsername(),menuInst_UserRoleChk,true,104,true,true,true);
            systemMenuAuthorizationRepository.save(systemMenuAuthorizationInst);
        }
        //Set to SystemMenuAuthorization
        SystemMenu menuInst_RequestMapChk = systemMenuRepository.getByCode("AUTH_REQUEST_MAP");
        systemMenuAuthorizationInst = this.systemMenuAuthorizationRepository.findBySystemMenu(menuInst_RequestMapChk);
        if(systemMenuAuthorizationInst==null){
            User userGeneral = this.userRepository.getUserByUsername("system");
            systemMenuAuthorizationInst = new SystemMenuAuthorization("AUTH_REQUEST_MAP",null,userGeneral.getUsername(),menuInst_RequestMapChk,true,104,true,true,true);
            systemMenuAuthorizationRepository.save(systemMenuAuthorizationInst);
        }
        //Set to SystemMenuAuthorization

        SystemMenu menuInst_MenuDefChk = systemMenuRepository.getByCode("SYS_MENU_DEF");
        systemMenuAuthorizationInst = this.systemMenuAuthorizationRepository.findBySystemMenu(menuInst_MenuDefChk);
        if(systemMenuAuthorizationInst==null){
            User userGeneral = this.userRepository.getUserByUsername("system");
            systemMenuAuthorizationInst = new SystemMenuAuthorization("SYS_MENU_DEF",null,userGeneral.getUsername(),menuInst_MenuDefChk,true,105,true,true,true);
            systemMenuAuthorizationRepository.save(systemMenuAuthorizationInst);
        }
        //Set to SystemMenuAuthorization
        SystemMenu menuInst_sysMenuAuthorizationChk = systemMenuRepository.getByCode("SYS_MENU_AUTH");
        systemMenuAuthorizationInst = this.systemMenuAuthorizationRepository.findBySystemMenu(menuInst_sysMenuAuthorizationChk);
        if(systemMenuAuthorizationInst==null){
            User userGeneral = this.userRepository.getUserByUsername("system");
            systemMenuAuthorizationInst = new SystemMenuAuthorization("SYS_MENU_AUTH",null,userGeneral.getUsername(),menuInst_sysMenuAuthorizationChk,true,106,true,true,true);
            systemMenuAuthorizationRepository.save(systemMenuAuthorizationInst);
        }

    }






    @Override
    public void run(String... args) throws Exception {
        System.out.println("------>CommandLineAppStartupRunner<----------");
        this.createAppBasicRoles();
        this.createAppBasicUsers();
        this.createSystemMenu();
        this.createSystemMenuAuthorization();
    }
}
