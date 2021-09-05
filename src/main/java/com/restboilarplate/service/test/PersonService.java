package com.restboilarplate.service.test;

import com.restboilarplate.dto.PersonDTO;
import com.restboilarplate.entity.test.Location;
import com.restboilarplate.entity.test.Person;
import com.restboilarplate.repository.test.LocationRepository;
import com.restboilarplate.repository.test.PersonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Person create(PersonDTO personDTO) {
        return this.personRepository.save(convertToPerson2(personDTO));
    }

    private Person convertToPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setUsername(personDTO.getUsername());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
     //   person.setLocation(locationRepository.findById(personDTO.getLocation()).get());
        return person;
    }

    private Person convertToPerson2(PersonDTO personDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Person person=new Person();
        person.setUsername(personDTO.getUsername());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
     //   person.setLocation(locationRepository.findById(personDTO.getLocation()).get());
        person = modelMapper.map(personDTO, Person.class);
        return person;
    }

    public List<PersonDTO> getAllPersonLocationMapper() {
        return ((List<Person>) personRepository.findAll()).stream().map(this::convertToPersonDto)
                .collect(Collectors.toList());
    }

    private PersonDTO convertToPersonDto(Person person) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }
}
