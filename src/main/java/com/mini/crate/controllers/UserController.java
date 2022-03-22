package com.mini.crate.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.crate.domain.Role;
import com.mini.crate.domain.User;
import com.mini.crate.security.jwt.JwtUtils;
import com.mini.crate.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;
	private final JwtUtils jwtUtils;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}

	@PostMapping("/user/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
		return ResponseEntity.created(uri).body(userService.save(user));
	}

	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}

	@PostMapping("/role/user")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
		userService.addRoleToUser(form.getUserName(), form.getRoleName());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				DecodedJWT jwt = jwtUtils.decodeJwtToken(refreshToken);

				String username = jwt.getSubject();
				User user = userService.getUser(username);

				String accessToken = jwtUtils.encodeToken(
						user.getUserName(),
						new Date(System.currentTimeMillis() + 10 * 60 * 1000),
						request.getRequestURL().toString(),
						user.getRoles().stream().map(Role::getName).collect(Collectors.toList())
				);


				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);
				tokens.put("refresh_token", refreshToken);
				tokens.put("path", request.getServletPath());

				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);

			} catch (Exception exception) {
				createErrorResponse(request, response, exception);
			}
		} else {
			throw new RuntimeException("Refresh token is missing");
		}

	}

	public static void createErrorResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
		response.setHeader("error", exception.getMessage());
		response.setStatus(FORBIDDEN.value());
		response.setContentType(APPLICATION_JSON_VALUE);

		final Map<String, Object> error = new HashMap<>();
		error.put("status", FORBIDDEN);
		error.put("error", "FORBIDDEN");
		error.put("message", exception.getMessage());
		error.put("path", request.getServletPath());

		new ObjectMapper().writeValue(response.getOutputStream(), error);
	}
}

@Data
class RoleToUserForm {
	private String userName;
	private String roleName;
}
