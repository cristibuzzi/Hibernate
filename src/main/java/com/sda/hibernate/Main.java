package com.sda.hibernate;

import com.sda.hibernate.models.Account;
import com.sda.hibernate.models.Department;
import com.sda.hibernate.models.Employee;
import com.sda.hibernate.models.Project;
import com.sda.hibernate.repository.*;
import com.sda.hibernate.utils.SessionManager;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // SessionManager.getSessionFactory();

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
        ProjectRepository projectRepository = new ProjectRepositoryImpl();

        Employee employee = new Employee(
                "Jititel",
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

        Employee employeeFromDb2 = employeeRepository.findEmployeeById(1).orElse(null);
        System.out.println(employeeFromDb2);
        System.out.println("-------");
        System.out.println(employeeFromDb2.getAccount());

        Employee employee2 = new Employee(
                "Vladut",
                "Tepusa",
                Date.from(Instant.now().minus(Duration.ofDays(5000L))),
                "121212",
                "vlad.tepes@yahoo.com",
                1200
        );

        employeeRepository.createEmployee(employee2);

        Department department = new Department("Hibernate");

        departmentRepository.createDepartment(department);

        employee.setDepartment(department);
        employeeRepository.updateEmployee(employee);

        employee2.setDepartment(department);
        employeeRepository.updateEmployee(employee2);

        Department departmentFromDb = departmentRepository.finndByIdAndLoadEmployees(1).orElse(null);
        System.out.println(departmentFromDb);
        System.out.println(departmentFromDb.getEmployees());

        Project project1 = new Project("hibernate1");
        projectRepository.createProject(project1);
        Project project2 = new Project("hibernate2");
        projectRepository.createProject(project2);

        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);

        employee.setProjects(projects);
        employeeRepository.updateEmployee(employee);
        employee2.setProjects(projects);
        employeeRepository.updateEmployee(employee2);

        Project projectFromDateBase = projectRepository.findByIdAndLoadEmployees(1).orElse(null);
        System.out.println(projectFromDateBase);
        System.out.println(projectFromDateBase.getEmployees());

        System.out.println("--------------------------------------------------");

        employeeRepository.getAllEmployeesWithNamesStartingWithJ()
                .stream()
                .forEach(e -> {
                    System.out.println(e);
                    System.out.println(e.getAccount());
                    System.out.println(e.getDepartment());
                });

//        Employee employeeToBeDeleted = new Employee();
//        employeeToBeDeleted.setId(1);
//        employeeRepository.deleteEmployee(employeeToBeDeleted);

        // SessionManager.shutDown();
    }
}
