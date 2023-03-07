package com.sda.hibernate.repository;

import com.sda.hibernate.models.Account;


import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    void createAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Account account);

    Optional<Account> findAccountById(int id);

    List<Account> findAllAccounts();
}
