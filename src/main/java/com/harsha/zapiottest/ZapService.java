package com.harsha.zapiottest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ZapService {

	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private AddressRepository addRepo;
	
	public Person addPerson(Person p) {
		return personRepo.save(p);
	}

	public Person editPerson(Person p) {
		personRepo.deleteById(p.getId());
		return personRepo.save(p);
	}

	public String deletePerson(Long id) {
		String message = "";
		try {
			personRepo.deleteById(id);
			message = "Deleted successfully.";
		} catch(EmptyResultDataAccessException ex) {
			message = "Record not available for id " + id;
		} catch(Exception e) {
			message = "Delete failure.";
		}
		return message;
	}

	public List<Address> addAddress(List<Address> a) {
		return StreamSupport.stream(addRepo.saveAll(a).spliterator(), false).collect(Collectors.toList());
	}

	public Address editAddress(Address a) {
		addRepo.deleteById(a.getAddrId());
		return addRepo.save(a);
	}

	public String deleteAddress(Long id) {
		String message = "";
		try {
			addRepo.deleteById(id);
			message = "Deleted successfully.";
		} catch (EmptyResultDataAccessException ex) {
			message = "Record not available for id " + id;
		} catch (Exception e) {
			message = "Delete failure.";
		}
		return message;
	}

	public int count() {
		return (int) personRepo.count();
	}

	public List<Person> listPersons() {	
		return StreamSupport.stream(personRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

}
