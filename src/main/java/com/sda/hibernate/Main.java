package com.sda.hibernate;

import com.sda.hibernate.models.Employee;
import com.sda.hibernate.repository.EmployeeRepository;
import com.sda.hibernate.repository.EmployeeRepositoryImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
       // SessionManager.getSessionFactory();

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

        Employee employee = new Employee(
                "Titel",
                "Aurel",
                Date.from(Instant.now().minus(Duration.ofDays(5000L))),
                "3434343",
                "titel.aurel@yahoo.com",
                850

        );
        employeeRepository.createEmployee(employee);

        Employee employeeFromDatabase = employeeRepository.findEmployeeById(1).orElse(null);
        System.out.println(employeeFromDatabase);

        employeeFromDatabase.setEmail("titititit@yahoo.updatat");
        employeeRepository.updateEmployee(employeeFromDatabase);
        System.out.println(employeeRepository.findEmployeeById(1));

        employeeRepository.getAllEmployees()
                .forEach(System.out::println);







        Employee employeeToBeDeleted = new Employee();
        employeeToBeDeleted.setId(1);
        employeeRepository.deleteEmployee(employeeToBeDeleted);

       // SessionManager.shutDown();
    }
}
