package com.restboilarplate.acl.auth.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.restboilarplate.entity.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACL_USERS")
public class User extends BaseEntity implements UserDetails {


    @NotBlank(message = "*Name is mandatory")
    @Column(name = "USERNAME", length = 15, nullable = false, unique = true)
    private String username;

    @NotBlank
    private String password;
    private String firstName;
    private String lastName;

    @Email
    @NotBlank(message = "email must not be empty")
    private String email;
    private String phone;
    private boolean enabled=false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "acl_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set=new HashSet<>();

        this.roles.forEach(role -> {
            set.add(new Authority(role.getRoleName()));
        });
        return set;
    }

    public User(String username, String email, String firstName, String lastName, String phone,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
