package com.licious.app.controller;
import com.licious.app.controller.response.ErrorResponse;
import com.licious.app.controller.response.SuccessResponse;
import com.licious.app.dto.EmployeeDTO;
import com.licious.app.model.Employee;
import com.licious.app.service.EmployeeService;
import com.licious.app.utils.EmployeeMapper;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;
    private SuccessResponse successResponse=new SuccessResponse();
    private ErrorResponse errorResponse=new ErrorResponse();


    @GetMapping("/employee/all")
    @ResponseBody
    public ResponseEntity showAllEmployees(Pageable pageable)
    {
        Page<Employee> employeeList=employeeService.getAllEmployees(pageable);
        //List<EmployeeDTO> employeeDTOList=employeeMapper.employeeListToemployeeDTOList(employeeList);
        return ResponseBuilder.
                getSuccessResponse(employeeList,"All the employee records are found");
    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity getEmployeeById(@PathVariable  int id)
    {
        Employee employee=employeeService.getEmployeeById(id);
        EmployeeDTO employeeDTO=employeeMapper.employeeToEmployeeDTO(employee);
        String message="Employee with id: "+id+" has been returned";
        return ResponseBuilder.getSuccessResponse(employeeDTO,message);

    }

    @DeleteMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity deleteEmployee(@PathVariable int id)
    {
        String responseMessage=employeeService.deleteEmployee(id);
        return ResponseBuilder.getSuccessResponse(responseMessage,responseMessage);
    }

    @PostMapping(value="/employee/new",consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addEmployee(@RequestBody Employee employee)
    {
        String responseMsg=employeeService.createEmployee(employee);
        if (responseMsg == "success") {
            Employee newEmployee=employeeService.getNewlyAddedEmployee();
            String msg="New employee with id="+newEmployee.getId()+" has been created";
            EmployeeDTO newEmployeeDTO=employeeMapper.employeeToEmployeeDTO(employee);
            return ResponseBuilder.getSuccessResponse(newEmployeeDTO,msg);
        }
        String msg="Employee addition is failure";
        return ResponseBuilder.getErrorResponse(msg,responseMsg,HttpStatus.CONFLICT);

    }

    @PutMapping(value="/employee",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateEmployee(@RequestBody Employee employee, @RequestParam int id
    )
    {
       String responseMsg=employeeService.editEmployee(employee,id);
       EmployeeDTO employeeDTO=employeeMapper.
            employeeToEmployeeDTO(employeeService.getEmployeeById(id));
        return ResponseBuilder.getSuccessResponse(employeeDTO,responseMsg);

    }
}
