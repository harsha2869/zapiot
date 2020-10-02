package com.harsha.zapiottest;

import java.util.List;
import java.util.Optional;
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

	public Optional<Person> editPerson(Person p) {
		Optional<Person> savedRecord = personRepo.findById(p.getId());
		if(savedRecord.isPresent()) {
			if(null != savedRecord.get().getAddress() 
					&& !savedRecord.get().getAddress().isEmpty()) {
				addRepo.deleteAll(savedRecord.get().getAddress());
			}
			personRepo.delete(savedRecord.get());
			personRepo.save(p);
			return personRepo.findById(p.getId());
		} else {
			return Optional.empty();
		}
	}

	public String deletePerson(Long id) {
		String message = "";
		try {
			personRepo.deleteById(id);
			message = "Person record removed successfully.";
		} catch(EmptyResultDataAccessException ex) {
			message = "Person record not available for id " + id;
		} catch(Exception e) {
			message = "Delete failure.";
		}
		return message;
	}

	public Optional<Person> addAddress(Person p) {
		Optional<Person> savedRecord = personRepo.findById(p.getId());
		if(savedRecord.isPresent()) {
			personRepo.save(p);
			return personRepo.findById(p.getId());
		} else {
			return Optional.empty();
		}
	}

	public Optional<Person> editAddress(Person p) {
		
		Optional<Person> savedRecord = personRepo.findById(p.getId());
		if(savedRecord.isPresent()) {
			if(null != savedRecord.get().getAddress() 
					&& !savedRecord.get().getAddress().isEmpty()) {
				addRepo.deleteAll(savedRecord.get().getAddress());
			}
			personRepo.delete(savedRecord.get());
			personRepo.save(p);
			return personRepo.findById(p.getId());
		} else {
			return Optional.empty();
		}		
	}

	public String deleteAddress(Long id) {
		String message = "";
		try {
			addRepo.deleteById(id);
			message = "Address record removed successfully.";
		} catch (EmptyResultDataAccessException ex) {
			message = "Address record not available for id " + id;
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
