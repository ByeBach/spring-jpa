package com.bach.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.bach.curso.springboot.jpa.springbootjpa.dto.PersonDto;
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
		//delete2();
		//personalizedQuery();
		//personalizedQuery2();
		//personalizedQueriesDistinct();
		personalizedQueriesConcatUpperandLowerCase();
	}

	@Transactional
	public void personalizedQueriesConcatUpperandLowerCase(){
		System.out.println("========================= consultas con nombres y apellidos de personas ==============================");
		List<String> names = repository.findAllFullnameConcat();
		names.forEach(System.out::println);
	}
	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct(){
		System.out.println("consultas con nombres de personas");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("========================= consultas con nombres unicos de personas ==============================");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("========================= consultas lenguages unicos de personas ==============================");
		List<String> languages = repository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("========================= consulta con total de lenguaje de programacion unicas ==============================");
		Long totalLenguage = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println("total de lenguajes de programacion: " + totalLenguage);
		
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

	@Transactional(readOnly = true)
	public void personalizedQuery2(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("========================= consulta por objeto persona y lenguaje de programacion ==============================");
		List<Object[]> personsRegs = repository.findAllMixPersonDataList();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguege= " + reg[1] + ", " + reg[0]);
		});

		System.out.println("consulta que puebla y devulve el objeto entity de uns instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(person -> System.out.println(person));


		System.out.println("consulta que puebla de devuelve objeto dto de una clase personalizada");
		List<PersonDto> personsdto = repository.findAllPersonDto();
		personsdto.forEach(System.out::println);

		scanner.close();
	}

	@Transactional(readOnly = true)
	public void personalizedQuery(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("========================= consulta solo el nombre por el ID ==============================");
		System.out.println("ingrese el id");
		Long id = scanner.nextLong();
		scanner.close();

		String name = repository.getNameById(id);
		System.out.println(name);
		
		System.out.println("========================== el nombre completo es:=========================================");
		String fullname = repository.getFullnameById(id);
		System.out.println(fullname);


		System.out.println("========================== consulta campo personalizado por id:=========================================");
		Object[] personReg = (Object[]) repository.obtenerPersonDataById(id);
		System.out.println("ID: " + personReg[0] + "| NAME: " + personReg[1] + "| LASTNAME: " + personReg[2] + "| LENGUAGE: " + personReg[3]);

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
				Person personUpdate = repository.save(person);
				System.out.println(personUpdate);
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
