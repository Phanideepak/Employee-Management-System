package com.licious.app.aspect;

import com.licious.app.model.Department;
import com.licious.app.utils.Validation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DepartmentAspectService {


    @Around(value="execution(* com.licious.app.service.DepartmentRepositoryService.createNewDepartment(..)) and args(department)")
    public void aroundAdviceCreateDepartment(ProceedingJoinPoint joinPoint, Department department)
            throws Throwable {
        joinPoint.proceed();
       if(Validation.checkDepartmentRequestBody(department)==false){
           throw new RuntimeException("Invalid department");
       }

       System.out.println("Department is valid");
    }
}
