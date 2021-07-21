package com.licious.app.aspect;

import com.licious.app.model.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeAspectService {
    @Before(value="execution(* com.licious.app.service.EmployeeService.getEmployeeById(..)) and args(id)")
    public void beforeAdviceOnGetEmployee(JoinPoint joinPoint,int id){
        System.out.println("Before method: "+joinPoint.getSignature());
        System.out.println("requested for employee with id="+id);
    }
    @After(value="execution(* com.licious.app.service.EmployeeService.getEmployeeById(..)) and args(id)")
    public void afterAdviceOnGetEmployee(JoinPoint joinPoint,int id){
        System.out.println("After method execution: "+joinPoint.getSignature());
        System.out.println("employee with id:"+id+" has been fetched");
    }

    @Around(value="execution(* com.licious.app.service.EmployeeService.editEmployee(..)) and args(emp,id)")
    public void aroundAdviceOnUpdateEmployee(ProceedingJoinPoint proceedingJoinPoint, Employee emp, int id){
      System.out.println("Employee before updation: "+emp.toString());
        try {
            // Proceed from point before method execution to point after method execution
            // triggers method execution.
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Employee after updation: "+emp);
    }
    @AfterReturning(value=
            "execution(* com.licious.app.service.EmployeeRepositoryService.getEmployeeByEmail(..)) and args(email)",
    returning = "employee")
    public void afterReturningAdviceOnGetEmployee(JoinPoint joinPoint,Employee employee,String email){
        System.out.println("After returning advice invoked");
        System.out.println(employee);
    }

    @AfterThrowing(value="execution(* com.licious.app.service.EmployeeRepositoryService.getEmployeeByEmail(..)) and args(email)"
            ,throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint,String email,Exception ex){
        System.out.println(ex.getMessage());
    }

}
