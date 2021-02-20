package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1 {
	
	@GetMapping("/test2")
	public String write1() {
		return "Hello World1!";
	}
}
