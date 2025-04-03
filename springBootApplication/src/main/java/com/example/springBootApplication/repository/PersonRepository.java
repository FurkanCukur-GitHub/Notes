package com.example.springBootApplication.repository;

import com.example.springBootApplication.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
