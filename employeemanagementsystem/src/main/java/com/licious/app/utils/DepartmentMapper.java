package com.licious.app.utils;

import com.licious.app.dto.DepartmentDTO;
import com.licious.app.model.Department;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department departmentDTOToDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO departmentToDepartmentDTO(Department department);

   // Page<DepartmentDTO> departmentListToDepartmentDTOList(Page<Department> departmentList);
}
