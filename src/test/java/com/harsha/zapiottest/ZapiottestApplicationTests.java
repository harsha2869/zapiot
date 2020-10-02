package com.harsha.zapiottest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ZapiottestApplicationTests {
	
	public final static String ADD_PERSON_SUCCESS_MSG = "Person added successfully";
	public final static String EDIT_PERSON_SUCCESS_MSG = "Person updated successfully";
	public final static String DELETE_PERSON_SUCCESS_MSG = "Person record removed successfully.";
	public final static String DELETE_ADDRESS_SUCCESS_MSG = "Address record removed successfully.";
	
	@InjectMocks
	private ZapController controller;
	
	@Mock
	private ZapService service;

	@Test
	void addPerson() {
		ZapRequest request = new ZapRequest();
		Person person = new Person();
		person.setId(100L);
		person.setFirstname("harsha");
		person.setLastname("gadiraju");
		Set<Address> addresses = new HashSet<Address>();
		Address address = new Address();
		address.setStreet("mint");
		address.setCity("chennai");
		address.setState("Tamilnadu");
		address.setPostalcode("600001");
		addresses.add(address);
		person.setAddress(addresses);
		when(service.addPerson(request.getPerson())).thenReturn(person);
		ResponseEntity<ZapResponse> zapResponse = controller.addPerson(request);
		assertTrue(HttpStatus.OK.equals(zapResponse.getStatusCode()));
		assertTrue(ADD_PERSON_SUCCESS_MSG.equalsIgnoreCase(zapResponse.getBody().getMessage()));
		assertTrue(person.equals(zapResponse.getBody().getPerson()));
	}
	
	@Test
	void editPerson() {
		ZapRequest request = new ZapRequest();
		Person person = new Person();
		person.setId(100L);
		person.setFirstname("harsha");
		person.setLastname("gadiraju");
		Set<Address> addresses = new HashSet<Address>();
		Address address = new Address();
		address.setStreet("mint");
		address.setCity("chennai");
		address.setState("Tamilnadu");
		address.setPostalcode("600001");
		addresses.add(address);
		person.setAddress(addresses);
		Optional<Person> p = Optional.of(person);
		when(service.editPerson(request.getPerson())).thenReturn(p);
		ResponseEntity<ZapResponse> zapResponse = controller.editPerson(request);
		assertTrue(HttpStatus.OK.equals(zapResponse.getStatusCode()));
		assertTrue(EDIT_PERSON_SUCCESS_MSG.equalsIgnoreCase(zapResponse.getBody().getMessage()));
		assertTrue(person.equals(zapResponse.getBody().getPerson()));
	}
	
	@Test
	void deletePerson() {
		Long id = 100L;
		String message = "Person record removed successfully.";
		when(service.deletePerson(id)).thenReturn(message);
		ResponseEntity<String> zapResponse = controller.deletePerson(id);
		assertTrue(HttpStatus.OK.equals(zapResponse.getStatusCode()));
		assertTrue(DELETE_PERSON_SUCCESS_MSG.equalsIgnoreCase(zapResponse.getBody()));
	}
	
	@Test
	void deleteAddress() {
		Long id = 100L;
		String message = "Address record removed successfully.";
		when(service.deletePerson(id)).thenReturn(message);
		ResponseEntity<String> zapResponse = controller.deletePerson(id);
		assertTrue(HttpStatus.OK.equals(zapResponse.getStatusCode()));
		assertTrue(DELETE_ADDRESS_SUCCESS_MSG.equalsIgnoreCase(zapResponse.getBody()));
	}

}
