package org.example.daos;

import org.example.entities.User;
import org.example.exceptions.LibraryExceptions;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDao extends GenericDao<User, Long> {
    private final Session session;

    public UserDao(Session session) {
        super(session, User.class);
        this.session = session;
    }

    public User create(User user) {
        if (user.getUserId() == null) {
            if (findByUsername(user.getUsername()) != null) {
                throw LibraryExceptions.existsByUsername(user.getUsername());
            }
            return super.createOrUpdate(user);
        } else {
            throw LibraryExceptions.idNotNull();
        }
    }

    public User findByUsername(String username) {
        String query = "select u from User u where u.username = :username";
        Query<User> findByUsernameQuery = session.createQuery(query, User.class);
        findByUsernameQuery.setParameter("username", username);
        return findByUsernameQuery.getSingleResultOrNull();
    }

    public User update(String firstName, String lastName, Long id) {
        User user = findById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return super.createOrUpdate(user);
    }

}