package com.sda.hibernate.repository;

import com.sda.hibernate.models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void createProject(Project project);

    void updateProject(Project project);

    void deleteProject(Project project);

    Optional<Project> findById(int id);

    Optional<Project> findByIdAndLoadEmployees(int id);

    List<Project> findAllProject();
}
