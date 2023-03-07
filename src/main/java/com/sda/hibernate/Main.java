package com.sda.hibernate;

import com.sda.hibernate.models.Account;
import com.sda.hibernate.models.Employee;
import com.sda.hibernate.repository.AccountRepository;
import com.sda.hibernate.repository.AccountRepositoryImpl;
import com.sda.hibernate.repository.EmployeeRepository;
import com.sda.hibernate.repository.EmployeeRepositoryImpl;


import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
       // SessionManager.getSessionFactory();

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl();

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


        Account account = new Account("77777", new Date());
        accountRepository.createAccount(account);

        // create OneToOne relation

        employee.setAccount(account);
        employeeRepository.updateEmployee(employee);

        Employee employeeFromDb2= employeeRepository.findEmployeeById(1).orElse(null);
        System.out.println(employeeFromDb2);
        System.out.println("-------");
        System.out.println(employeeFromDb2.getAccount());

//        Employee employeeToBeDeleted = new Employee();
//        employeeToBeDeleted.setId(1);
//        employeeRepository.deleteEmployee(employeeToBeDeleted);

       // SessionManager.shutDown();
    }
}
