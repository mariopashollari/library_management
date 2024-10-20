package org.example.config;

import org.example.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class HibernateUtils {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            Configuration configuration = new Configuration();
            configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Publisher.class);
            configuration.addAnnotatedClass(Request.class);
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

}