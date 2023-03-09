package com.sda.hibernate.repository;

import com.sda.hibernate.models.Employee;


import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Optional<Employee> findEmployeeById(Integer id);

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployeesWithNamesStartingWithJ();
}
