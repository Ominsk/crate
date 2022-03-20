package com.mini.crate.controllers;

import com.mini.crate.models.Person;
import com.mini.crate.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {

	PersonService personService;

	@GetMapping("/api/v1/persons/")
	public List<Person> getAllPerson() {
		return personService.getAll();
	}

	@GetMapping("/api/v1/person/{name}")
	public Person getPersonWithName(@PathVariable(value="name") String name) {
		return personService.getPersonByName(name);
	}

	@PostMapping("/api/v1/person/add/{name}")
	public void addPerson(@PathVariable(value="name") String name) {
		personService.addPerson(name);
	}
}
