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

import com.example.Service.EmployeeService;
import com.example.dto.EmployeeDto;
import com.example.entity.Employee;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody final EmployeeDto employeeDto) {
		Employee employee = employeeService.addEmployee(Employee.from(employeeDto));
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();
		List<EmployeeDto> employeesDto = employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
		return new ResponseEntity<>(employeesDto, HttpStatus.OK);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final Long id) {
		Employee employee = employeeService.getEmployee(id);
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable final Long id) {
		Employee employee = employeeService.deleteEmployee(id);
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<EmployeeDto> editEmployee(@PathVariable final Long id,
			@Valid @RequestBody final EmployeeDto employeeDto) {
		Employee employee = employeeService.editEmployee(id, Employee.from(employeeDto));
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}

	@PostMapping(value = "{employeeId}/addresses/{addressId}/add")
	public ResponseEntity<EmployeeDto> addAddressToEmployee(@PathVariable final Long employeeId,
			@PathVariable final Long addressId) {
		Employee employee = employeeService.addAddressToEmployee(employeeId, addressId);
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}

	@DeleteMapping(value = "{employeeId}/addresses/{addressId}/remove")
	public ResponseEntity<EmployeeDto> removeAddressFromEmployee(@PathVariable final Long employeeId,
			@PathVariable final Long addressId) {
		Employee employee = employeeService.removeAddressFromEmployee(employeeId, addressId);
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final String email) {
		Employee employee = employeeService.getEmployeeByEmail(email);
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}

	@GetMapping(value = "/{firstName}/{lastName}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final String firstName,
			@PathVariable String lastName) {
		Employee employee = employeeService.get(firstName, lastName);
		return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
	}
}
