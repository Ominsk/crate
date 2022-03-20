package com.mini.crate.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {

	@Id @GeneratedValue private Long id;

	private String name;

	@Relationship(type = "TEAMMATE")
	public Set<Person> teammates;


	public Person(String name) {
		this.name = name;
	}
}
