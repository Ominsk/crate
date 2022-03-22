package com.mini.crate.services;

import com.mini.crate.domain.Role;
import com.mini.crate.domain.User;

import java.util.List;

public interface UserService {

	User save(User user);
	Role saveRole(Role role);
	void addRoleToUser(String userName, String roleName);
	User getUser(String userName);
	List<User> getUsers();

	void deleteAll();
}
