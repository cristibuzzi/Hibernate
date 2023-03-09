package com.sda.hibernate.repository;

import com.sda.hibernate.models.Department;
import com.sda.hibernate.models.Employee;
import com.sda.hibernate.models.Project;
import com.sda.hibernate.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {
    @Override
    public void createProject(Project project) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(project);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateProject(Project project) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(project);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(Project project) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(project);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Project> findById(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Project project = session.find(Project.class, id);
            return Optional.ofNullable(project);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Project> findByIdAndLoadEmployees(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Project project = session.find(Project.class, id);
            System.out.println(project.getEmployees().size());
            return Optional.ofNullable(project);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Project> findAllProject() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Query<Project> query = session.createQuery(
                    "select e from Project e",
                    Project.class
            );
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
