package com.bach.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String lastName;

    @Column(name = "programming_lenguage")
    private String programmingLenguage;

    //jpa utiliza un constructor sin argumentos para crear el objeto
    public Person() {
    }
    public Person(Long id, String name, String lastName, String programmingLenguage) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.programmingLenguage = programmingLenguage;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getProgrammingLenguage() {
        return programmingLenguage;
    }
    public void setProgrammingLenguage(String programmingLenguage) {
        this.programmingLenguage = programmingLenguage;
    }
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", lastName=" + lastName + ", programmingLenguage="
                + programmingLenguage + "]";
    }
    
    
}
