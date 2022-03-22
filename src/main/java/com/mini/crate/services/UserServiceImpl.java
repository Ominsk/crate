package com.mini.crate.services;

import com.mini.crate.domain.Role;
import com.mini.crate.domain.User;
import com.mini.crate.repositories.RoleRepo;
import com.mini.crate.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional // save directly the modified obj
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {
		log.info("Saving user {} to database", user.getUserName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving role {} to database", role.getName());

		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		log.info("Adding role {} to user {}", roleName, userName);

		User user = userRepo.findUserByUserName(userName);
		Role role = roleRepo.findRoleByName(roleName);

		user.getRoles().add(role);
		userRepo.save(user);
	}

	@Override
	public User getUser(String userName) {
		log.info("Fetch user {}", userName);

		return userRepo.findUserByUserName(userName);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetch all users");

		return userRepo.findAll();
	}

	@Override
	public void deleteAll() {
		log.info("deleted all userService");
		userRepo.deleteAll();
		roleRepo.deleteAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findUserByUserName(username);
		if (user == null) {
			log.error("User not find");
			throw new UsernameNotFoundException("User " + username + " not found");
		} else {
			log.info("User {} found", username);
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
	}
}
