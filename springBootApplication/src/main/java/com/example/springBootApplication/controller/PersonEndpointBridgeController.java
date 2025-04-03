package com.example.springBootApplication.controller;

import com.example.springBootApplication.endpoint.PersonEndpoint;
import com.example.springBootApplication.entity.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator/person-bridge")
public class PersonEndpointBridgeController {

    private final PersonEndpoint personEndpoint;

    @Autowired
    public PersonEndpointBridgeController(PersonEndpoint personEndpoint) {
        this.personEndpoint = personEndpoint;
    }

    @PostMapping("/json")
    public PersonDto postPersonJson(@RequestBody PersonDto dto) {
        return personEndpoint.addPerson(dto.getName(), dto.getSurname(), dto.getNumber());
    }
}
