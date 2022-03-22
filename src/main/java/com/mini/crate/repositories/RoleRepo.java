package com.mini.crate.repositories;

import com.mini.crate.domain.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RoleRepo extends Neo4jRepository<Role, Long> {

	Role findRoleByName(String name);
}
