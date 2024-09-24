package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByEmail(String Email);

	@Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
	Employee find(String firstName, String lastName);
}
