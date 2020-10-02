package com.harsha.zapiottest;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long addrId;
	private String street;
	private String city;
	private String state;
	private String postalcode;

	public Long getAddrId() {
		return addrId;
	}
	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	
	@Override
	public int hashCode() {
		return street.hashCode() + city.hashCode() + state.hashCode() + postalcode.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(null == obj || this.getClass() != obj.getClass()) return false;
		Address address = (Address) obj;
		return this.street != null && this.street.equals(address.street) 
				&& this.city != null && this.city.equals(address.city)
				&& this.state != null && this.state.equals(address.state)
				&& this.postalcode != null && this.postalcode.equals(address.postalcode);
	}
	
}
