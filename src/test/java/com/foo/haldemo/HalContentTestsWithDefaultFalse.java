package com.foo.haldemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.hateoas.use-hal-as-default-json-media-type=false")
class HalContentTestsWithDefaultFalse {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	Integer port;

	@Test
	void checkContentTypeForAcceptHal() {

		RequestEntity<Void> req = RequestEntity.get(URI.create("http://localhost:"+port+"/foo"))
				.header("accept", "application/hal+json")
				.build();

		ResponseEntity<String> res = restTemplate.exchange(req, String.class);
		String contentType = res.getHeaders().getContentType().toString();
		assert contentType.equals("application/hal+json");

	}

	@Test
	void checkContentTypeForAcceptJson() {

		RequestEntity<Void> req = RequestEntity.get(URI.create("http://localhost:"+port+"/foo"))
				.header("accept", "application/json")
				.build();

		ResponseEntity<String> res = restTemplate.exchange(req, String.class);
		String contentType = res.getHeaders().getContentType().toString();
		assert contentType.equals("application/json");

	}

	@Test
	void checkContentTypeForNoAccept() {

		RequestEntity<Void> req = RequestEntity.get(URI.create("http://localhost:"+port+"/foo"))
				.build();

		ResponseEntity<String> res = restTemplate.exchange(req, String.class);
		String contentType = res.getHeaders().getContentType().toString();
		assert contentType.equals("application/json");

	}

	@Test
	void checkContentTypeForAcceptAny() {

		RequestEntity<Void> req = RequestEntity.get(URI.create("http://localhost:"+port+"/foo"))
				.header("accept", "*/*")
				.build();

		ResponseEntity<String> res = restTemplate.exchange(req, String.class);
		String contentType = res.getHeaders().getContentType().toString();
		assert contentType.equals("application/json");

	}

	@Test
	void checkContentTypeForAcceptTextPlain() {

		RequestEntity<Void> req = RequestEntity.get(URI.create("http://localhost:"+port+"/foo"))
				.header("accept", "text/plain")
				.build();

		ResponseEntity<String> res = restTemplate.exchange(req, String.class);
		assert res.getStatusCode() == HttpStatus.NOT_ACCEPTABLE;

	}

}
