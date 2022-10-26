package org.khasanof.serviceone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServiceOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOneApplication.class, args);
	}

}


@RestController
@RequestMapping(value = "/api/*")
class ServiceController {

	@RequestMapping(value = "say", method = RequestMethod.GET)
	public ResponseEntity<String> say() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

}