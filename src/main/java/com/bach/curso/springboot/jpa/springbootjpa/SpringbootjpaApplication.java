package com.bach.curso.springboot.jpa.springbootjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bach.curso.springboot.jpa.springbootjpa.entities.Person;
import com.bach.curso.springboot.jpa.springbootjpa.repository.PersonRepository;

@SpringBootApplication
public class SpringbootjpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("java");
		List<Person> persons = (List<Person>) repository.buscarByprogrammingLanguage("Java", "Andres");
		
		persons.stream().forEach(person -> System.out.println(person));
	}

}
