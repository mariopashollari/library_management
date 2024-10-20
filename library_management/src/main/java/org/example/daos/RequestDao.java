package org.example.daos;

import org.example.entities.Book;
import org.example.entities.Request;
import org.example.entities.User;
import org.example.exceptions.LibraryExceptions;
import org.example.static_data.RequestStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class RequestDao extends GenericDao<Request, Long> {
    private final Session session;
    private final BookDao bookDao;

    public RequestDao(Session session, BookDao bookDao) {
        super(session, Request.class);
        this.session = session;
        this.bookDao = bookDao;
    }

    public Request create(Request request, User user, List<Book> books) {
        if (request.getRequestId() == null) {
            request.setUser(user);
            request.setStatus(RequestStatus.CREATED);
            request.setCreatedAt(LocalDateTime.now());
            request.setReturnedAt(LocalDateTime.now().plusMonths(1));
            bookDao.takeBooks(books);
            return super.createOrUpdate(request);
        } else {
            throw LibraryExceptions.idNotNull();
        }
    }

    public List<Request> findAllByUsername(String username) {
        String query = "select r from Request r where r.user.username = :username" +
                " and r.status != :status";
        Query<Request> findAllByUsernameQuery = session.createQuery(query, Request.class);
        findAllByUsernameQuery.setParameter("username", username);
        findAllByUsernameQuery.setParameter("status", RequestStatus.CANCELED);
        return findAllByUsernameQuery.getResultList();
    }

    public void finishRequest(Long id) {
        Request request = findById(id);
        request.setStatus(RequestStatus.COMPLETED);
        request.setReturnedAt(LocalDateTime.now());
        bookDao.returnBooks(request.getBooks());
        super.createOrUpdate(request);
    }

}
