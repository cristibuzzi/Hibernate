package com.sda.hibernate.models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @OneToOne(mappedBy = "account")
    private Employee employee;


    public Account() {

    }

    public Account(String accountNumber, Date dateOfCreation) {
        this.accountNumber = accountNumber;
        this.dateOfCreation = dateOfCreation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
