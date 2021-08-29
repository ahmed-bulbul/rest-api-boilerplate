package com.restboilarplate;

import com.restboilarplate.acl.auth.entity.Role;
import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.acl.auth.repository.RoleRepository;
import com.restboilarplate.acl.auth.repository.UserRepository;
import com.restboilarplate.acl.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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





    @Override
    public void run(String... args) throws Exception {
        System.out.println("------>CommandLineAppStartupRunner<----------");
        this.createAppBasicRoles();
        this.createAppBasicUsers();
    }
}
