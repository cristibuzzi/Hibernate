package com.sda.hibernate.repository;

import com.sda.hibernate.models.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.sda.hibernate.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public void createEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> findEmployeeById(Integer id) {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            Employee employee = session.find(Employee.class,id);
            return Optional.ofNullable(employee);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            Query<Employee> query = session.createQuery(
                    "select e from Employee e",
                    Employee.class
            );
            return query.list();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
