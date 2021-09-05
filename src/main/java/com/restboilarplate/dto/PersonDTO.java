package com.restboilarplate.dto;

import com.restboilarplate.entity.test.Location;
import lombok.Data;

@Data
public class PersonDTO {
    private String username;
    private String firstName;
    private String lastName;
    //private Location location;
    private String place;
}
