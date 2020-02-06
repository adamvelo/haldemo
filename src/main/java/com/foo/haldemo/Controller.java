package com.foo.haldemo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/foo")
	public ResponseEntity<Response> getFoo() {
		return ResponseEntity.ok(new Response("foo", linkTo(methodOn(Controller.class).getFoo()).withSelfRel()));
	}
}
