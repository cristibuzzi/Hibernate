package com.sda.hibernate.utils;

import com.sda.hibernate.models.Employee;
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
    }

    public static SessionFactory getSessionFactory(){
        return INSTANCE.getSessionFactory(DATABASE_NAME);
    }

    public static void shutDown(){
        INSTANCE.shutdownSessionManager();
    }
}
