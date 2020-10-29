package com.sid.web;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sid.dao.ClientRepository;
import com.sid.entities.Client;

@RestController
public class ApiTestController {
	@Autowired
	private ClientRepository clientRepository;
	
//	@GetMapping(value = "/client/{code}")
//	public ResponseEntity<Client> getClient(@PathVariable long code ) {
//		Client client = clientRepository.getOne(code);
//		return new ResponseEntity<Client>(client, HttpStatus.FOUND);
//	}
	@GetMapping(value = "/clients")
	public ResponseEntity<Collection<Client>> getAllUsers() {
		Collection<Client> client = clientRepository.findAll();
		return new ResponseEntity<Collection<Client>>(client, HttpStatus.FOUND);
	}
	@GetMapping(value = "/client/{code}")
	public ResponseEntity<Client> findUserById(@PathVariable(value = "code") Long code) {
		Optional<Client> client = clientRepository.findById(code);
		return new ResponseEntity<Client>(client.get(), HttpStatus.FOUND);
	}
}
