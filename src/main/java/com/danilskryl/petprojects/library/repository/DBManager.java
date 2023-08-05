package com.danilskryl.petprojects.library.repository;

import com.danilskryl.petprojects.library.model.Book;
import com.danilskryl.petprojects.library.model.User;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

@Getter
public class DBManager {
    private final SessionFactory sessionFactory;

    public DBManager() {
        Properties properties = new Properties();
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/danilskryl");
        properties.put(Environment.USER, "danilskryl");
        properties.put(Environment.PASS, "ufkrbyf123");
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
//        User user = new User();
//        user.setName("Danil");
//        user.setSurname("Skryl");
//        user.setAge(22);
//        user.setBooks(new ArrayList<>());
//        user.setUsername("restill");
//        user.setPassword("secret123");

        LibraryManager libraryManager = LibraryManager.getInstance();
//        libraryManager.saveUser(user);

//        System.out.println(libraryManager.isUserExist("restill", "secret123"));
//        System.out.println(libraryManager.isUserExist("restill", "sofe2323"));
//        System.out.println(libraryManager.isUserExist("rerere", "secret123"));
    }
}