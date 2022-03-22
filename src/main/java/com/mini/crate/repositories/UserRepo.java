package com.mini.crate.repositories;

import com.mini.crate.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepo extends Neo4jRepository<User, Long> {

	User findUserByUserName(String username);
}
