package com.licious.app.utils;
import com.licious.app.dto.EmployeeDTO;
import com.licious.app.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source="employee.department.id",target="departmentId")
    EmployeeDTO employeeToEmployeeDTO(Employee employee);


    @Mapping(source="employeeDTO.departmentId",target="department.id")
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> employeeListToemployeeDTOList(List<Employee> employeeList);
}
