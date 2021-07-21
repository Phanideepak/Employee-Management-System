package com.licious.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class EmployeemanagementsystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeemanagementsystemApplication.class, args);
	}

}
