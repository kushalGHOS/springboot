package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.AddressService;
import com.example.dto.AddressDto;
import com.example.entity.Address;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;

	@PostMapping
	public ResponseEntity<AddressDto> addAddress(@Valid @RequestBody final AddressDto addressDto) {
		Address ad = addressService.addAddress(Address.from(addressDto));
		return new ResponseEntity<>(AddressDto.from(ad), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<AddressDto>> getAddress() {
		List<Address> ad = addressService.getAddress();
		List<AddressDto> adDto = ad.stream().map(AddressDto::from).collect(Collectors.toList());
		return new ResponseEntity<>(adDto, HttpStatus.OK);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<AddressDto> getAddress(@PathVariable final Long id) {
		Address ad = addressService.getAddress(id);
		return new ResponseEntity<>(AddressDto.from(ad), HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<AddressDto> deleteAddress(@PathVariable final Long id) {
		Address item = addressService.deleteAddress(id);
		return new ResponseEntity<>(AddressDto.from(item), HttpStatus.OK);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<AddressDto> editAddress(@PathVariable final Long id,
			@Valid @RequestBody final AddressDto aDto) {
		Address a = addressService.editAddress(id, Address.from(aDto));
		return new ResponseEntity<>(AddressDto.from(a), HttpStatus.OK);
	}

	@GetMapping(value = "/street/{street}")
	public ResponseEntity<AddressDto> getAddressbystreet(@PathVariable final String street) {
		Address ad = addressService.getAddressByStreet(street);
		return new ResponseEntity<>(AddressDto.from(ad), HttpStatus.OK);
	}

	@GetMapping(value = "/{street}/{state}")
	public ResponseEntity<AddressDto> getAddressbystreetAndState(@PathVariable final String street,
			@PathVariable final String state) {
		Address ad = addressService.get(street, state);
		return new ResponseEntity<>(AddressDto.from(ad), HttpStatus.OK);
	}
}
