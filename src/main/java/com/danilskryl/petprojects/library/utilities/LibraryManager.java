package com.danilskryl.petprojects.library.utilities;

import com.danilskryl.petprojects.library.model.Book;
import com.danilskryl.petprojects.library.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LibraryManager {
    private static DBManager dbManager;

    private LibraryManager() {}

    public DBManager getDBManager() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
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
