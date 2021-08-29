package com.restboilarplate.entity;


import com.restboilarplate.entity.baseEntity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @Column(name = "email", nullable = false, length = 200)
    @NotEmpty(message = "Email must not be empty")
    private String email;


    public Person() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
