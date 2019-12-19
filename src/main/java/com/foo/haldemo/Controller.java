package com.foo.haldemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/foo")
	public ResponseEntity<Response> getFoo(){
		return ResponseEntity.ok(new Response("foo"));
	}

}
