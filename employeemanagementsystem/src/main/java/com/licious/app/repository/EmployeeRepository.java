package com.licious.app.repository;

import com.licious.app.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
     public Optional<Employee> findOneByEmail(String email);

}
