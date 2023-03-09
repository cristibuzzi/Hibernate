package com.sda.hibernate.repository;

import com.sda.hibernate.models.Department;
import com.sda.hibernate.models.Employee;
import com.sda.hibernate.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository{
    @Override
    public void createDepartment(Department department) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(department);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepartment(Department department) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(department);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepartment(Department department) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(department);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Department> findById(int id) {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            Department department = session.find(Department.class,id);
            return Optional.ofNullable(department);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Department> finndByIdAndLoadEmployees(int id) {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            Department department = session.find(Department.class,id);
            System.out.println(department.getEmployees().size());
            return Optional.ofNullable(department);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Department> findAllDepartmend() {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            Query<Department> query = session.createQuery(
                    "select e from Department e",
                    Department.class
            );
            return query.list();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
