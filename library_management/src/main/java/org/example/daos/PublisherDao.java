package org.example.daos;

import org.example.entities.Publisher;
import org.example.entities.User;
import org.example.exceptions.LibraryExceptions;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PublisherDao extends GenericDao<Publisher, Long> {
    private final Session session;

    public PublisherDao(Session session) {
        super(session, Publisher.class);
        this.session = session;
    }

    public Publisher create(Publisher publisher) {
        if (publisher.getPublisherId() == null) {
            return super.createOrUpdate(publisher);
        } else {
            throw LibraryExceptions.idNotNull();
        }
    }

    public Publisher findByName (String name){
        String query = "select p from Publisher p where p.name = :name";
        Query<Publisher> findByNameQuery = session.createQuery(query, Publisher.class);
        findByNameQuery.setParameter("name", name);
        return findByNameQuery.getSingleResultOrNull();
    }

    public Publisher update(String name, String address, Long id) {
        Publisher publisher = findById(id);
        publisher.setName(name);
        publisher.setAddress(address);
        return super.createOrUpdate(publisher);
    }

}
