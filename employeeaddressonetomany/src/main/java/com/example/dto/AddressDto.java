package com.example.dto;

import java.util.Objects;

import com.example.entity.Address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class AddressDto {
	private Long id;
	@NotEmpty
	@Size(min = 4, message = "street name must be min of four charecters")
	private String street;
	@NotEmpty
	@Size(min = 4, message = "city name must be min of four charecters")
	private String city;
	@NotEmpty
	@Size(min = 4, message = "state name must be min of four charecters")
	private String state;
	@NotEmpty
	@Size(min = 7, message = " zipcode must be min of seven charecters")
	private String zipCode;
	private PlainEmployeeDto plainEmployeeDto;

	public static AddressDto from(Address i) {
		AddressDto ad = new AddressDto();
		ad.setId(i.getId());
		ad.setCity(i.getCity());
		ad.setStreet(i.getStreet());
		ad.setState(i.getState());
		ad.setZipCode(i.getZipCode());
		if (Objects.nonNull(i.getEmployee())) {

			ad.setPlainEmployeeDto(PlainEmployeeDto.from(i.getEmployee()));
		}
		return ad;
	}

}
