package com.sda.hibernate.repository;

import com.sda.hibernate.models.Account;
import com.sda.hibernate.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {


    @Override
    public void createAccount(Account account) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public Optional<Account> findAccountById(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Account account = session.find(Account.class, id);
            return Optional.ofNullable(account);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            Query<Account> query = session.createQuery(
                    "select a from Account a"
            );
            return query.list();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
