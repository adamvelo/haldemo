package com.foo.haldemo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class Response extends EntityModel<String> {

	public Response(String content, Link... links) {
		super(content, links);
	}
}
