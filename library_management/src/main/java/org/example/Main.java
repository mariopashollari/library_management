package org.example;

import org.example.config.HibernateUtils;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        SessionFactory sessionFactory = hibernateUtils.getSessionFactory();
    }
}
