package com.example.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.exception.AddressNotFoundException;
import com.example.repository.AddressRepository;

import jakarta.transaction.Transactional;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public Address addAddress(Address ad) {
		return addressRepository.save(ad);
	}

	public List<Address> getAddress() {
		return StreamSupport.stream(addressRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Address getAddress(Long id) {
		return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
	}

	public Address deleteAddress(Long id) {
		Address ad = getAddress(id);
		addressRepository.delete(ad);
		return ad;
	}

	public Address getAddressByStreet(String street) {
		return addressRepository.findByStreet(street);
	}

	public Address get(String street, String state) {
		return addressRepository.find(street, state);
	}

	@Transactional
	public Address editAddress(Long id, Address ad) {
		Address ad1 = getAddress(id);
		ad1.setCity(ad.getCity());
		ad1.setState(ad.getState());
		ad1.setStreet(ad.getStreet());
		ad1.setZipCode(ad.getZipCode());
		return ad1;
	}
}
