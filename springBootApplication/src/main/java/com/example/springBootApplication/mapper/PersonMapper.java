package com.example.springBootApplication.mapper;

import com.example.springBootApplication.entity.Person;
import com.example.springBootApplication.entity.PersonDto;

public class PersonMapper {

    public static PersonDto toDto(Person person) {
        if (person == null) {
            return null;
        }
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setNumber(person.getNumber());
        return dto;
    }

    public static Person toEntity(PersonDto dto) {
        if (dto == null) {
            return null;
        }
        Person person = new Person();
        // If you're creating a new person, the id might be null.
        person.setId(dto.getId());
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setNumber(dto.getNumber());
        return person;
    }
}
