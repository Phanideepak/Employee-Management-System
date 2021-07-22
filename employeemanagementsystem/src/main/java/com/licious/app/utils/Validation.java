package com.licious.app.utils;

import com.licious.app.model.Department;
import com.licious.app.repository.DepartmentRepository;
import com.licious.app.service.DepartmentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Validation {
    @Autowired
    private static DepartmentRepositoryService departmentRepositoryService;
    public static boolean checkDepartmentRequestBody(Department department){
          List<String> deptNames=departmentRepositoryService.getAllDepartmentNames();
          if(department.getDeptName().trim().equals("")){
              return false;
          }
          // duplicate departments are not allowed.
          if(deptNames.indexOf(department.getDeptName().toLowerCase())>0){
              return false;
          }
          return true;
    }
}
