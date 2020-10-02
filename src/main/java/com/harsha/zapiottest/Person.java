package com.harsha.zapiottest;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Person implements Serializable, Cloneable {
	
	@Id
	@Column(unique = true)
	private Long id;
	
	private String firstname;
	private String lastname;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID", nullable = false)
	private Set<Address> address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Set<Address> getAddress() {
		return address;
	}
	public void setAddress(Set<Address> address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(null == obj || this.getClass() != obj.getClass()) return false;
		Person person = (Person) obj;
		return this.id == person.id;
	}
	
}
