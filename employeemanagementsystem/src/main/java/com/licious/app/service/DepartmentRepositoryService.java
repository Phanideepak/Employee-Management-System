package com.licious.app.service;

import com.licious.app.exceptions.ServiceException;
import com.licious.app.model.Department;
import com.licious.app.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentRepositoryService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public String createNewDepartment(Department department)
    {

        if(getAllDepartmentNames().indexOf(department.getDeptName().toLowerCase())>0) {
            System.out.println("hello");
            throw new ServiceException("Insertion Error! Duplicate Department not allowed","Insert");
            //return "Insertion Error! Duplicate Department not allowed Insert ";
        }
        departmentRepository.save(department);
        System.out.println("Hello");
        return "success";
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).orElseThrow(()->new ServiceException("department",id));
    }

    public Page<Department> getAllDepartments(Pageable pageable){
        Page<Department> deptList=departmentRepository.findAll(pageable);
        if(deptList.isEmpty()){
            throw new ServiceException("departments");
        }
        return deptList;
    }
    public String deleteDepartmentById(int id)
    {
        Department dept= (departmentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("department", id)));

        departmentRepository.deleteById(id);

        return "department with id: "+id+" is deleted";

    }
    public String updateDepartment(int id,String deptName,String username)
    {
       Department department=departmentRepository.
               findById(id).orElseThrow(()->new ServiceException("department",id));
       department.setLastUpdatedDate(new Date());
       department.setLastUpdatedBy(username);
       department.setDeptName(deptName);
       departmentRepository.save(department);
       return "Department with id: "+id +" has been updated";
    }
    public Department getNewlyAddedDepartment()
    {
        List<Department> depts=departmentRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        return depts.get(0);
    }
    public List<String> getAllDepartmentNames()
    {
        List<String> deptNames=departmentRepository.findAll()
                .stream().map(dept->dept.getDeptName().toLowerCase()).collect(Collectors.toList());
        return deptNames;
    }
}
