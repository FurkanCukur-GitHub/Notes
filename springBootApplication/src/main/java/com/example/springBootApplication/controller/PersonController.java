package com.example.springBootApplication.controller;

import com.example.springBootApplication.entity.Person;
import com.example.springBootApplication.entity.PersonDto;
import com.example.springBootApplication.mapper.PersonMapper;
import com.example.springBootApplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final Logger logger;
    private final PersonService personService;

    @Autowired
    public PersonController(@Qualifier("furkan") Logger logger, PersonService personService) {
        this.logger = logger;
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) {
        logger.info("Yeni kişi kaydediliyor: " + personDto.getName());
        Person person = PersonMapper.toEntity(personDto);
        Person savedPerson = personService.savePerson(person);
        PersonDto savedDto = PersonMapper.toDto(savedPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        logger.info("Tüm kişiler getiriliyor...");
        List<Person> persons = personService.getAllPersons();
        List<PersonDto> personDtos = persons.stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(personDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        logger.info("Kişi getiriliyor, ID: " + id);
        Person person = personService.getPersonById(id);
        return person != null ? ResponseEntity.ok(PersonMapper.toDto(person))
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        logger.info("Kişi güncelleniyor, ID: " + id);
        Person existingPerson = personService.getPersonById(id);
        if (existingPerson == null) {
            return ResponseEntity.notFound().build();
        }
        Person person = PersonMapper.toEntity(personDto);
        person.setId(id);
        Person updatedPerson = personService.updatePerson(person);
        PersonDto updatedDto = PersonMapper.toDto(updatedPerson);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        logger.info("Kişi siliniyor, ID: " + id);
        Person existingPerson = personService.getPersonById(id);
        if (existingPerson == null) {
            return ResponseEntity.notFound().build();
        }
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}
