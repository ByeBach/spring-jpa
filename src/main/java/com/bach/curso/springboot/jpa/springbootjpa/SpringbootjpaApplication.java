package com.bach.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		//findOne();
		//create();
		//update();
		delete2();
	}
	@Transactional(readOnly = true)
	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("java");
		//List<Person> persons = (List<Person>) repository.buscarByprogrammingLanguage("Java", "Andres");
		List<Person> persons = (List<Person>) repository.findByprogrammingLanguageAndName("Java", "Andres");
		
		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personValues = repository.obtenerPersonData("maria");

		personValues.stream().forEach(person -> 
			{System.out.println(person[0] + " le sabe a " + person[1]);
		});
	}
	@Transactional
	public void delete(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese el id de la persona a eliminar");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);

		repository.deleteById(id);

		scanner.close();
	}
	@Transactional
	public void delete2(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese el id de la persona a eliminar");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(person -> repository.deleteById(person.getId()), () -> System.out.println("no existe el usario"));

		scanner.close();
	}
	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese el id de la persona a editar");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);

		//optionalPerson.ifPresent(person -> {
			if(optionalPerson.isPresent()){
				Person person = optionalPerson.orElseThrow();
				
				System.out.println(person);
				System.out.println("ingrese el lenguaje de programacion");
				String programmingLanguage = scanner.next();
				person.setProgrammingLanguage(programmingLanguage);
				Person persondb = repository.save(person);
				System.out.println(persondb);
			}else{
				System.out.println("el usuario no esta presente! no exite");
			}
			
		//});
		scanner.close();
	
	}
	@Transactional
	public void create(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese nombre");
		String name = scanner.next();
		System.out.println("ingrese apellido");
		String lastname = scanner.next();
		System.out.println("ingrese lenguaje de programacion");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}
	@Transactional(readOnly = true)
	public void findOne(){
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);
		// //f(!optionalPerson.isEmpty()){
		// if(optionalPerson.isPresent()){
		// 	person = optionalPerson.get();
		// } 
		// System.out.println(person);
		
		repository.findOneLikeName("ri").ifPresent(person -> System.out.println(person));

	}

}
