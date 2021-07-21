package com.licious.app.service;

import com.licious.app.exceptions.ServiceException;
import com.licious.app.model.Department;
import com.licious.app.model.Employee;
import com.licious.app.repository.DepartmentRepository;
import com.licious.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class EmployeeRepositoryService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

   public Page<Employee> getAllEmployees(Pageable pageable) {
       Page<Employee> employeeList=employeeRepository.findAll(pageable);
       if(employeeList.isEmpty()){
           throw  new ServiceException("employee");
       }
       return employeeList;
   }

   public Employee getEmployeeById(int id) {
       return employeeRepository.findById(id).orElseThrow(()->new ServiceException("employee",id));
   }

   public String deleteEmployee(int id){
       Employee employee=employeeRepository.findById(id).
               orElseThrow(()->new ServiceException("employee",id));
       employeeRepository.deleteById(id);
       return "department with id="+id+" has been deleted";

   }

   public String updateEmployee(int id,Employee employee){
       Employee existedEmployee=employeeRepository.findById(id)
               .orElseThrow(()->new ServiceException("employee",id));

       existedEmployee.setFirstName(employee.getFirstName());
       existedEmployee.setLastName(employee.getFirstName());
       existedEmployee.setEmail(employee.getEmail());
       existedEmployee.setPhoneNumber(employee.getPhoneNumber());
       existedEmployee.setDob(employee.getDob());
       existedEmployee.setJob(employee.getJob());
       existedEmployee.setJoiningDate(employee.getJoiningDate());
       existedEmployee.setCreatedBy(employee.getCreatedBy());
       existedEmployee.setLastUpdatedBy(employee.getLastUpdatedBy());
       existedEmployee.setSalary(employee.getSalary());
       //existedEmployee.setDepartmentId(employee.getDepartmentId());
       existedEmployee.setLastUpdatedDate(new Date());

       employeeRepository.save(existedEmployee);

       return "employee with id="+id+" has been updated";

   }

   public String addEmployee(Employee emp){

       emp.setCreatedAt(new Date());
       emp.setLastUpdatedDate(new Date());
       Assert.notNull(emp,"Employee must not be null");
       System.out.println(emp.getDepartment());
       Assert.notNull(emp.getDepartment(),"Department must not be null");

       // costly operation
       Optional<Employee> optEmp = employeeRepository.findOneByEmail(emp.getEmail());
       Optional<Department> optDep = departmentRepository.findById(emp.getDepartment().getId());

       if(optEmp.isPresent()){
                 throw new ServiceException("Insertion failure! Email already existed","insert");
       }
       if(!optDep.isPresent()){
             throw new ServiceException("Insertion failure because mentioned department id doesnot exist," +
                   "add employee to a existing department","insert");
       }
       employeeRepository.save(emp);
       return "success";

   }

   public Employee getNewlyAddedEmployee(){

       List<Employee> employeeList=employeeRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
       return employeeList.get(0);
   }
   public Employee getEmployeeByEmail(String email){
       return employeeRepository.findOneByEmail(email).get();
   }
}
