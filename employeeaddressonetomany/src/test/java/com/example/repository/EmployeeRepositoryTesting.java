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

import com.example.dto.EmployeeDto;
import com.example.entity.Employee;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTesting {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployee() {
		EmployeeDto e = new EmployeeDto();
		e.setEmail("ghoshkushal95@gmail.com");
		e.setFirstName("kushal");
		e.setLastName("ghosh");
		Employee e1 = employeeRepository.save(Employee.from(e));

		assertThat(e1.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getEmployee() {
		Employee e = employeeRepository.findById(1l).get();
		assertThat(e.getId()).isEqualTo(1L);

	}

	@Test
	@Order(3)
	@Rollback(value = true)
	public void updateEmployee() {
		Employee e = employeeRepository.findById(1l).get();
		e.setFirstName("kunal");
		assertThat(e.getFirstName()).isEqualTo("kunal");
	}

	@Test
	@Order(4)
	@Rollback(value = true)
	public void deleteEmployee() {
		Employee e = employeeRepository.findById(1l).get();
		employeeRepository.delete(e);
		Employee e1 = null;
		Optional<Employee> o = employeeRepository.findById(1l);
		if (o.isPresent()) {
			e1 = o.get();
		}
		assertThat(e1).isNull();
	}
}
