package com.danilskryl.petprojects.library.repository;

import com.danilskryl.petprojects.library.model.Book;
import com.danilskryl.petprojects.library.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public void removeUser(Long id) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        }
    }

    public void removeUser(User user) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
    }

    public boolean isUserExist(String login, String password) {
        return getUserByLoginAndPassword(login, password) != null;
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

    public Book getBookById(Long id) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    public void saveBook(Book book) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        }
    }

    public void removeBook(Long id) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            session.remove(book);
            transaction.commit();
        }
    }

    public void removeBook(Book book) {
        try (Session session = dbManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(book);
            transaction.commit();
        }
    }
}
