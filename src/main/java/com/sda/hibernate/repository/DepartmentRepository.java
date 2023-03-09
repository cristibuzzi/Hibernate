package com.sda.hibernate.repository;

import com.sda.hibernate.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    void createDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(Department department);

    Optional<Department> findById(int id);
    Optional<Department> finndByIdAndLoadEmployees(int id);

    List<Department> findAllDepartmend();
}
