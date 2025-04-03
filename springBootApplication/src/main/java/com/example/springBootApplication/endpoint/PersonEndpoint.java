package com.example.springBootApplication.endpoint;

import com.example.springBootApplication.entity.Person;
import com.example.springBootApplication.entity.PersonDto;
import com.example.springBootApplication.mapper.PersonMapper;
import com.example.springBootApplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "person")
public class PersonEndpoint {

    private final PersonService personService;

    @Autowired
    public PersonEndpoint(PersonService personService) {
        this.personService = personService;
    }

    @WriteOperation
    public PersonDto addPerson(String name, String surname, int number) {
        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        person.setNumber(number);
        return PersonMapper.toDto(personService.savePerson(person));
    }
}
