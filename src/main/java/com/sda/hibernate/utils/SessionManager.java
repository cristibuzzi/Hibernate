package com.sda.hibernate.utils;

import com.sda.hibernate.models.Account;
import com.sda.hibernate.models.Department;
import com.sda.hibernate.models.Employee;
import com.sda.hibernate.models.Project;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager extends AbstractSessionManager{

    private static final SessionManager INSTANCE= new SessionManager();

    private static final String DATABASE_NAME = "hibernate_demo_1";

    private SessionManager(){

    }
    @Override
    protected void setAnnotatedClasses(Configuration configuration) {
        // add hibernate model here
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Project.class);
    }

    public static SessionFactory getSessionFactory(){
        return INSTANCE.getSessionFactory(DATABASE_NAME);
    }

    public static void shutDown(){
        INSTANCE.shutdownSessionManager();
    }
}
