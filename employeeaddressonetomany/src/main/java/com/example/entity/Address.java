package com.example.entity;

import com.example.dto.AddressDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String street;
	private String city;
	private String state;
	private String zipCode;
	@ManyToOne
	private Employee employee;

	public static Address from(AddressDto ad) {
		Address a = new Address();
		a.setStreet(ad.getStreet());
		a.setCity(ad.getCity());
		a.setState(ad.getState());
		a.setZipCode(ad.getZipCode());
		return a;
	}

}
