package com.mini.crate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id @GeneratedValue
	private Long id;
	private String name;
	private String userName;
	private String password;
	private String email;
	private boolean locked;
	private boolean enabled;

	@Relationship(type = "HAS_ROLE")
	private Collection<Role> roles = new HashSet<>();
}
