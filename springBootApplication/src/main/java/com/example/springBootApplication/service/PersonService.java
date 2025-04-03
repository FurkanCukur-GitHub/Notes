package com.example.springBootApplication.service;

import com.example.springBootApplication.entity.Person;
import com.example.springBootApplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    @Transactional
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public void deletePersonById(Long personId) {
        personRepository.deleteById(personId);
    }
}

