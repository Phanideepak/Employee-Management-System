package com.licious.app.service;

import com.licious.app.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepositoryService departmentRepositoryService;

    public String updateDepartment(String deptName, int id, String username){

        return departmentRepositoryService.updateDepartment(id,deptName,username);

    }
    public String deleteDepartmentById(int id){

        return departmentRepositoryService.deleteDepartmentById(id);

    }
    public Department getDepartmentById(int id)
    {
        return departmentRepositoryService.getDepartmentById(id);
    }
    public Page<Department> getAllDepartments(Pageable pageable)
    {
        return departmentRepositoryService.getAllDepartments(pageable);
    }

    public String addNewDepartment(String deptName,String username)
    {
        try
        {
            Department dept=new Department();
            dept.setDeptName(deptName);
            dept.setCreatedAt(new Date());
            dept.setLastUpdatedBy(username);
            dept.setCreatedBy(username);
            dept.setLastUpdatedDate(new Date());

            return departmentRepositoryService.createNewDepartment(dept);
        }catch (Exception e)
        {
            return e.getMessage();
        }
    }
    public Department getNewlyAddedDepartment()
    {
        return departmentRepositoryService.getNewlyAddedDepartment();
    }

}
