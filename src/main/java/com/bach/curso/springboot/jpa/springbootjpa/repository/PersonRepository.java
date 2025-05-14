package com.bach.curso.springboot.jpa.springbootjpa.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.bach.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.bach.curso.springboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {


    @Query("select lower(CONCAT(p.name, ' ', p.lastname)) from Person p")
    List<String> findAllFullnameLower();

    @Query("select upper( p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullnameConcatUpper();

    //@Query("select CONCAT(p.name, ' ', p.lastname) from Person p")
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullnameConcat();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("select new com.bach.curso.springboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();


    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPersonDataList();

    @Query("select concat (p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullnameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p WHERE p.id=?1")
    Object obtenerPersonDataById(Long id);

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonDataList();

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
