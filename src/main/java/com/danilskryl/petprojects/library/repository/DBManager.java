package com.danilskryl.petprojects.library.repository;

import com.danilskryl.petprojects.library.model.Book;
import com.danilskryl.petprojects.library.model.User;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Getter
public class DBManager {
    private final SessionFactory sessionFactory;

    public DBManager() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        LibraryManager libraryManager = LibraryManager.getInstance();

        List<Book> books = libraryManager.getUserBooks(3L);
        for (Book book : books) {
            System.out.println(book.getId());
            System.out.println(book.getTitle());
            System.out.println(book.getAuthor());
            System.out.println(book.getUser());
        }
        System.out.println(books);
    }
}