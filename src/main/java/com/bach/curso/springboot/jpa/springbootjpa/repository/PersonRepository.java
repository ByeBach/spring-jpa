package com.bach.curso.springboot.jpa.springbootjpa.repository;
import org.springframework.data.repository.CrudRepository;


import com.bach.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
    
}
