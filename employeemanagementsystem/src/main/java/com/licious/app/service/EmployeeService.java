package com.licious.app.service;

import com.licious.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepositoryService employeeRepositoryService;

    public Page<Employee> getAllEmployees(Pageable pageable)
    {
           return employeeRepositoryService.getAllEmployees(pageable);
    }
    public Employee getEmployeeById(int id)
    {
      return employeeRepositoryService.getEmployeeById(id);
    }

    public String createEmployee(Employee emp)
    {
        try{
            return employeeRepositoryService.addEmployee(emp);
        }catch(RuntimeException e){
            return e.getMessage();
        }
    }

    public String editEmployee(Employee emp,int id)
    {
        return employeeRepositoryService.updateEmployee(id,emp);
    }
    public String deleteEmployee(int id)
    {
      return employeeRepositoryService.deleteEmployee(id);
    }

    public Employee getNewlyAddedEmployee(){
        return employeeRepositoryService.getNewlyAddedEmployee();
    }
}
