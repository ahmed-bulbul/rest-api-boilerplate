package com.restboilarplate.entity.test;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


@Setter
@Getter
@Entity
public class Location extends BaseEntity {

    private double lat;
    private double lng;
    private String place;
    private String description;
}
