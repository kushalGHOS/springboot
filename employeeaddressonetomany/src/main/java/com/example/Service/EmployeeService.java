package com.example.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Employee;
import com.example.exception.AddressAlreadyAssignedException;
import com.example.exception.EmployeeNotFoundException;
import com.example.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final AddressService addressService;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, AddressService addressService) {
		this.employeeRepository = employeeRepository;
		this.addressService = addressService;
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return StreamSupport.stream(employeeRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Employee deleteEmployee(Long id) {
		Employee employee = getEmployee(id);
		employeeRepository.delete(employee);
		return employee;
	}

	@Transactional
	public Employee editEmployee(Long id, Employee employee) {
		Employee employeeToEdit = getEmployee(id);
		employeeToEdit.setFirstName(employee.getFirstName());
		employeeToEdit.setLastName(employee.getLastName());
		employeeToEdit.setEmail(employee.getEmail());
		return employeeToEdit;
	}

	@Transactional
	public Employee addAddressToEmployee(Long employeeId, Long addressId) {
		Employee e = getEmployee(employeeId);
		Address ad = addressService.getAddress(addressId);
		if (Objects.nonNull(ad.getEmployee())) {
			throw new AddressAlreadyAssignedException(addressId, ad.getEmployee().getId());
		}
		e.addAddress(ad);
		ad.setEmployee(e);
		return e;
	}

	@Transactional
	public Employee removeAddressFromEmployee(Long employeeId, Long addressId) {
		Employee e = getEmployee(employeeId);
		Address ad = addressService.getAddress(addressId);
		e.remove(ad);
		return e;
	}

	public Employee getEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

	public Employee get(String firstName, String lastName) {
		return employeeRepository.find(firstName, lastName);
	}

}