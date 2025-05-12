package com.bach.curso.springboot.jpa.springbootjpa.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.bach.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    //Optional<Person> findByeNameContaing(String name); ocurre un error aunque no lo este llamando ?) wtf

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByprogrammingLanguage(String programmingLanguage, String name);

    List<Person> findByprogrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);
    

}
