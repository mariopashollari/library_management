package org.example.daos;

import org.example.exceptions.LibraryExceptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDao<E, ID> {
    private Session session;
    private Transaction transaction;
    private Class<E> clazz;

    public GenericDao(Session session, Class<E> clazz) {
        this.session = session;
        this.clazz = clazz;
    }

    protected E createOrUpdate(E entity) {
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    public List<E> findAll() {
        String findAll = String.format("select r from %s r", clazz.getSimpleName());
        Query<E> query = session.createQuery(findAll, clazz);
        return query.getResultList();
    }

    public E findById(ID id) {
        E e = session.find(clazz, id);
        if (e == null) {
            throw LibraryExceptions.notFound(clazz.getSimpleName(), id);
        } else {
            return e;
        }
    }

}