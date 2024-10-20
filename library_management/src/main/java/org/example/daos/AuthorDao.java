package org.example.daos;

import org.example.entities.Author;
import org.example.entities.Book;
import org.example.entities.Publisher;
import org.example.exceptions.LibraryExceptions;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorDao extends GenericDao<Author, Long> {
    private final Session session;

    public AuthorDao(Session session) {
        super(session, Author.class);
        this.session = session;
    }

    public Author create(Author author) {
        if(author.getAuthorId() == null) {
            return super.createOrUpdate(author);
        } else {
            throw LibraryExceptions.idNotNull();
        }
    }

    public List<Author> findByFirstNameAndLastName (String name){
        String query = "select author from Author author where " +
                "concat(author.firstName, ' ', author.lastName)  like :name";
        Query<Author> findByFirstNameAndLastNameQuery =
                session.createQuery(query, Author.class);
        findByFirstNameAndLastNameQuery.setParameter("name", "%" + name + "%");
        return findByFirstNameAndLastNameQuery.getResultList();
    }

    public Author update(String firstName, String lastName, Long id, String bio) {
        Author author = findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBio(bio);
        return super.createOrUpdate(author);
    }

    public void addBooksAuthor(Long authorId, List<Book> books) {
        Author author = findById(authorId);
        List<Book> existingBooks = author.getBooks();
        books.addAll(existingBooks);
        author.setBooks(books);
        createOrUpdate(author);
    }
}
