package com.restboilarplate.controller.test;


import com.restboilarplate.acl.auth.entity.User;
import com.restboilarplate.dto.PersonDTO;
import com.restboilarplate.entity.test.Person;
import com.restboilarplate.service.test.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/")
    public Person create(@RequestBody PersonDTO personDTO){
        return this.personService.create(personDTO);
    }

    @GetMapping("/")
    @ResponseBody
    public List<PersonDTO> getAllPersonLocationMapper() {
        List <PersonDTO> personLocation = personService.getAllPersonLocationMapper();
        return personLocation;
    }


}
