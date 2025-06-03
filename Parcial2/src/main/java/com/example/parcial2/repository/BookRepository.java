package com.example.parcial2.repository;

import com.example.parcial2.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthor(String author);

    List<Book> findByLanguage(String language);

    List<Book> findByGenre(String genre);

    List<Book> findByPagesBetween(int minPages, int maxPages);

    boolean existsByIsbn(String isbn);
}
