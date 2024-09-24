package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.dto.EmployeeDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private List<Address> address = new ArrayList<>();

	public void addAddress(Address ap) {
		address.add(ap);
	}

	public void remove(Address ap) {
		address.remove(ap);
	}

	public static Employee from(EmployeeDto e1) {
		Employee e = new Employee();
		e.setFirstName(e1.getFirstName());
		e.setLastName(e1.getLastName());
		e.setEmail(e1.getEmail());
		return e;
	}

}
