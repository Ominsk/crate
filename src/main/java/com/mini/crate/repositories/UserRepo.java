package com.mini.crate.repositories;

import com.mini.crate.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface UserRepo extends Neo4jRepository<User, Long> {

	Optional<User> findUserByUserName(String username);
}
