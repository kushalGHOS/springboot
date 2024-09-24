package com.example.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.entity.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDto {
	private Long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Email(message = "email address is not valid")
	private String email;
	private List<AddressDto> addressDto = new ArrayList<>();

	public static EmployeeDto from(Employee e) {
		EmployeeDto e1 = new EmployeeDto();
		e1.setId(e.getId());
		e1.setFirstName(e.getFirstName());
		e1.setLastName(e.getLastName());
		e1.setEmail(e.getEmail());
		e1.setAddressDto(e.getAddress().stream().map(AddressDto::from).collect(Collectors.toList()));
		return e1;
	}
}
