package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.dto.AddressDto;
import com.example.entity.Address;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressRepositoryTesting {
	@Autowired
	private AddressRepository addressRepository;

	@Test
	@Rollback(value = false)
	@Order(1)
	public void saveAddressTest() {
		System.out.println("------------savetest----------------");
		AddressDto ad = new AddressDto();
		ad.setCity("kolkata");
		ad.setState("bengal");
		ad.setStreet("bbstreet");
		ad.setZipCode("712200");
		Address i = addressRepository.save(Address.from(ad));
		assertThat(i.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getAddressTest() {
		Address a = addressRepository.findById(1l).get();
		AddressDto ad = AddressDto.from(a);
		assertThat(ad.getId()).isEqualTo(1l);
	}

	@Test
	@Order(3)
	@Rollback(value = false)
	public void updateAddress() {
		Address a = addressRepository.findById(1l).get();
		AddressDto ad = AddressDto.from(a);
		ad.setCity("dumdum");
		ad.setState("bengal");
		ad.setStreet("bbstreet");
		ad.setZipCode("712200");
		Address i = addressRepository.save(Address.from(ad));
		assertThat(i.getCity()).isEqualTo("dumdum");

	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void deleteAddress() {
		Address a = addressRepository.findById(1l).get();
		// AddressDto ad = AddressDto.from(a);
		addressRepository.delete(a);
		Address f = null;
		// Optional<Address> ad =
		// Optional.ofNullable(addressRepository.findByStreet("bbstreet"));
		Optional<Address> ad = addressRepository.findById(1l);
		if (ad.isPresent()) {
			f = ad.get();
		}
		assertThat(f).isNull();

	}

}
