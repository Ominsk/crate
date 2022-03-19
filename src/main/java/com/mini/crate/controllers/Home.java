package com.mini.crate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@GetMapping("/api/v1/home/")
	public String home() {

		return "Hello Docker World";
	}

	@GetMapping("/")
	public String root() {

		return "HI";
	}

}
