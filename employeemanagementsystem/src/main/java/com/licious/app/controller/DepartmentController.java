package com.licious.app.controller;

import com.licious.app.controller.response.ErrorResponse;
import com.licious.app.controller.response.SuccessResponse;
import com.licious.app.dto.DepartmentDTO;
import com.licious.app.model.Department;
import com.licious.app.repository.DepartmentRepository;
import com.licious.app.service.DepartmentService;
import com.licious.app.utils.DepartmentMapper;
import com.licious.app.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

    private SuccessResponse successResponse=new SuccessResponse();


    private ErrorResponse errorResponse=new ErrorResponse();

    @GetMapping("/department/all")
    public ResponseEntity<Page<Department>> getAllDepartments(Pageable pageable)
    {
        Page<Department> departmentList=departmentService.getAllDepartments(pageable);
        /*Page<DepartmentDTO> departmentDTOList=departmentMapper.departmentListToDepartmentDTOList(
                departmentList);*/
        String msg="All the department records are found";


        return ResponseBuilder.getSuccessResponse(departmentList,msg);


    }

    @GetMapping("/department/{id}")
    @ResponseBody
    public ResponseEntity<?> getDepartmentById(@PathVariable int id)
    {
        Department existingDepartment= departmentService.getDepartmentById(id);
        DepartmentDTO existingDepartmentDTO=departmentMapper.departmentToDepartmentDTO(existingDepartment);
        String msg="Department with id: "+id+ " is found";
        return ResponseBuilder.getSuccessResponse(existingDepartmentDTO,msg);
    }

    @PostMapping(value="/department/new")
    @ResponseBody
    public ResponseEntity<?> addNewDepartment(@RequestParam String deptName,
                                              @RequestHeader("username") String username)
    {
        String responseMsg=departmentService.addNewDepartment(deptName,username);
        if(responseMsg=="success"){
            Department newDept=departmentService.getNewlyAddedDepartment();
            String msg="Department with id: "+newDept.getId()+" is created";
            return ResponseBuilder.getSuccessResponse(newDept,msg);
        }

        String msg=responseMsg;
        String errorStackTrace="Insertion of department failed";
        return ResponseBuilder.getErrorResponse(msg,errorStackTrace,HttpStatus.CONFLICT);
    }


    @PutMapping(value="/department")
    @ResponseBody
    public ResponseEntity<?> updateDepartment(@RequestParam String deptName,@RequestParam int id,
                                   @RequestHeader("username") String username)
    {
          String responseMsg=departmentService.updateDepartment(deptName,id,username);
          Department dept=(departmentService.getDepartmentById(id));
          return ResponseBuilder.getSuccessResponse(dept,responseMsg);
    }

    @DeleteMapping("/department/{id}")
    @ResponseBody
    public  ResponseEntity<?> deleteDepartment(@PathVariable  int id)
    {
        String responseMsg=departmentService.deleteDepartmentById(id);
        String dataMsg="department has been deleted.";
        return ResponseBuilder.getSuccessResponse(dataMsg,responseMsg);
    }
}
