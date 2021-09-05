package com.restboilarplate.entity.test;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
public class Person extends BaseEntity {
    private String username;
    private String firstName;
    private String lastName;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "location_id")
    private Location location;
}
