package org.example.daos;

import org.example.entities.Author;
import org.example.entities.Book;
import org.example.exceptions.LibraryExceptions;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDao extends GenericDao<Book, Long> {
    private final Session session;
    private final AuthorDao authorDao;

    public BookDao(Session session, AuthorDao authorDao) {
        super(session, Book.class);
        this.session = session;
        this.authorDao = authorDao;
    }

    public Book create(Book book) {
        if(book.getBookId() == null) {
            return super.createOrUpdate(book);
        } else {
            throw LibraryExceptions.idNotNull();
        }
    }

    public void takeBooks(List<Book> books) {
        books.forEach(book -> {
            if(book.getQuantity() == 0) {
                throw LibraryExceptions.emptyListOfBooks(book.getTitle());
            } else {
                book.setQuantity(book.getQuantity() - 1);
                super.createOrUpdate(book);
            }
        });
    }

    public void returnBooks(List<Book> books) {
        books.forEach(book -> {
            book.setQuantity(book.getQuantity() + 1);
            super.createOrUpdate(book);
        });
    }

    public List<Book> findAllByAuthors(String name) {
        List<Author> authors =
                authorDao.findByFirstNameAndLastName(name);
        List<Book> books = new ArrayList<>();
        authors.forEach(author -> {
            books.addAll(author.getBooks());
        });
        return authors.stream()
                .filter(author -> author.getBooks() != null)
                .map(author -> author.getBooks())
                .flatMap(List::stream)
                .toList();
//        return books;
    }
}