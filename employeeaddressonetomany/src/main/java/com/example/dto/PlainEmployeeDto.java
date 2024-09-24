package com.example.dto;

import com.example.entity.Employee;

import lombok.Data;

@Data
public class PlainEmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	public static PlainEmployeeDto from(Employee e) {
		PlainEmployeeDto e1 = new PlainEmployeeDto();
		e1.setId(e.getId());
		e1.setFirstName(e.getFirstName());
		e1.setLastName(e.getLastName());
		e1.setEmail(e.getEmail());
		return e1;
	}
}
