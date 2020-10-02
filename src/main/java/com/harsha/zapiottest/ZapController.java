package com.harsha.zapiottest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZapController {
	
	@Autowired
	private ZapService service;
	
	@GetMapping("/")
	public String ping() {
		return "ping";
	}
	
	@PostMapping("/addPerson")
	public ResponseEntity<ZapResponse> addPerson(@RequestBody ZapRequest request) {
		ZapResponse zap = new ZapResponse();
		Person p = service.addPerson(request.getPerson());
		if(null != p) {
			zap.setPerson(p);
			zap.setMessage("Person added successfully");
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.OK); 
		} else {
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.BAD_REQUEST); 
		}
	}
	
	@PostMapping("/editPerson")
	public ResponseEntity<ZapResponse> editPerson(@RequestBody ZapRequest request) {
		ZapResponse zap = new ZapResponse();
		Person p = service.editPerson(request.getPerson());
		if(null != p) {
			zap.setPerson(p);
			zap.setMessage("Person updated successfully");
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.OK); 
		} else {
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.BAD_REQUEST); 
		}
		
	}

	@GetMapping("/deletePerson/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Long id) {

		String message = service.deletePerson(id);
		return new ResponseEntity<String>(message, HttpStatus.OK); 
	}

	
	@PostMapping("/addAddress")
	public ResponseEntity<ZapResponse> addAddress(@RequestBody ZapRequest request) {
		ZapResponse zap = new ZapResponse();
		List<Address> addresses = service.addAddress(request.getPerson().getAddress());

		if (null != addresses && !addresses.isEmpty()) {
			zap.setAddresses(addresses);
			zap.setMessage("Address added to person successfully");
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.OK);
		} else {
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/editAddress")
	public ResponseEntity<ZapResponse> editAddress(@RequestBody ZapRequest request) {

		ZapResponse zap = new ZapResponse();
		Address a = service.editAddress(request.getPerson().getAddress().get(0));

		if (null != a) {
			zap.setAddresses(Arrays.asList(a));
			zap.setMessage("Address updated successfully");
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.OK);
		} else {
			return new ResponseEntity<ZapResponse>(zap, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/deleteAddress/{id}")
	public ResponseEntity<Address> deleteAddress(@RequestBody Long id) {

		service.deleteAddress(id);
		return new ResponseEntity<Address>(HttpStatus.OK);
	}
	 

	@GetMapping("/personcount")
	public ResponseEntity<Integer> count() {
		int count = service.count();
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}

	@GetMapping("/listPersons")
	public ResponseEntity<List<Person>> listPersons() {

		List<Person> list_persons = service.listPersons();
		return new ResponseEntity<List<Person>>(list_persons, HttpStatus.OK);
	}
}
