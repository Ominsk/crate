package com.mini.crate.services;

import com.mini.crate.models.Person;
import com.mini.crate.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

	PersonRepository personRepository;


	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Person getPersonByName(String name) {
		return personRepository.findByName(name);
	}

	public void addPerson(String name) {
		Person person = new Person(name);
		personRepository.save(person);
	}
}
