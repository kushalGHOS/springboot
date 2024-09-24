package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByStreet(String street);

	@Query("select a from Address a where a.street=?1 and a.state=?2")
	Address find(String street, String state);
}
