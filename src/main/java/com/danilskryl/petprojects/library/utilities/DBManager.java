package com.danilskryl.petprojects.library.utilities;

import com.danilskryl.petprojects.library.model.Book;
import com.danilskryl.petprojects.library.model.User;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class DBManager {
    private final SessionFactory sessionFactory;

    public DBManager() {
        Properties fileProperties = new Properties();

        String url = null;
        String user = null;
        String pass = null;

        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            fileProperties.load(fis);

            url = fileProperties.getProperty("db.url");
            user = fileProperties.getProperty("db.user");
            pass = fileProperties.getProperty("db.password");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        Properties properties = new Properties();
        properties.put(Environment.URL, url);
        properties.put(Environment.USER, user);
        properties.put(Environment.PASS, pass);
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
}