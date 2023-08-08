package com.danilskryl.petprojects.library.repository;

import com.danilskryl.petprojects.library.model.Book;
import com.danilskryl.petprojects.library.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private static final LibraryManager libraryManager = new LibraryManager();
    private final DBManager dbManager;

    private LibraryManager() {
        dbManager = new DBManager();
    }

    public static LibraryManager getInstance() {
        return libraryManager;
    }

    public User getUserById(Long id) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public void saveUser(User user) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }

    public boolean isUserExist(String username) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE username = :USERNAME";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("USERNAME", username);
            User user = query.uniqueResult();
            return user != null;
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE username = :USER_LOGIN AND password = :USER_PASSWORD";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("USER_LOGIN", login);
            query.setParameter("USER_PASSWORD", password);
            try {
                return query.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public void addBookToUser(Long userId, Long bookId) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            User user = session.get(User.class, userId);
            book.setUser(user);
            user.getBooks().add(book);
            session.persist(book);
            session.persist(user);
            transaction.commit();
        }
    }

    public List<Book> getUserBooks(Long id) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Book> query = session.createQuery("FROM Book WHERE user.id = :USER_ID", Book.class);
            query.setParameter("USER_ID", id);
            List<Book> books = new ArrayList<>(query.list());
            transaction.commit();
            return books;
        }
    }

    public long saveBook(Book book) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return (long) session.getIdentifier(book);
        }
    }

    public void removeBook(Long id, Long userId) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            List<Book> books = user.getBooks();
            books.removeIf(book -> book.getId().equals(id));
            session.merge(user);

            Book book = session.get(Book.class, id);
            if (book != null) {
                session.remove(book);
            }

            transaction.commit();
        }
    }

    public void updateBook(Long id, String title, String author) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            book.setTitle(title);
            book.setAuthor(author);
            session.merge(book);
            transaction.commit();
        }
    }
}
