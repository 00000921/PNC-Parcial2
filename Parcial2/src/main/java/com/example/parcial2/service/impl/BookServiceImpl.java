package com.example.parcial2.service.impl;

import com.example.parcial2.domain.dto.request.*;
        import com.example.parcial2.domain.dto.response.*;
        import com.example.parcial2.domain.entity.Book;
import com.example.parcial2.exception.*;
        import com.example.parcial2.repository.BookRepository;
import com.example.parcial2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.*;
        import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public BookResponseDTO create(BookCreateDTO dto) {
        if (repository.existsByIsbn(dto.getIsbn())) {
            throw new DuplicateIsbnException("El ISBN ya existe");
        }
        if (!dto.getTitle().matches(".*[a-zA-Z]+.*")) {
            throw new IllegalArgumentException("El título no puede ser solo números");
        }
        if (dto.getPublicationYear() < 1900 || dto.getPublicationYear() > Year.now().getValue()) {
            throw new IllegalArgumentException("Año inválido");
        }
        if (dto.getPages() <= 10) {
            throw new IllegalArgumentException("Las páginas debe ser mayor a 10");
        }
        Book book = new Book(null, dto.getTitle(), dto.getAuthor(), dto.getIsbn(),
                dto.getPublicationYear(), dto.getLanguage(), dto.getPages(), dto.getGenre());
        repository.save(book);
        return mapToDTO(book);
    }

    @Override
    public List<BookResponseDTO> getAll(String author, String language, String genre, Integer minPages, Integer maxPages) {
        List<Book> books = repository.findAll();

        if (author != null) books = books.stream().filter(b -> b.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
        if (language != null) books = books.stream().filter(b -> language.equalsIgnoreCase(b.getLanguage())).collect(Collectors.toList());
        if (genre != null) books = books.stream().filter(b -> genre.equalsIgnoreCase(b.getGenre())).collect(Collectors.toList());
        if (minPages != null && maxPages != null) books = books.stream().filter(b -> b.getPages() >= minPages && b.getPages() <= maxPages).collect(Collectors.toList());

        return books.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getByIsbn(String isbn) {
        Book book = repository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("ISBN no encontrado"));
        return mapToDTO(book);
    }

    @Override
    public BookResponseDTO update(Long id, BookUpdateDTO dto) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));
        if (dto.getTitle() != null && dto.getTitle().matches(".*[a-zA-Z]+.*")) {
            book.setTitle(dto.getTitle());
        }
        if (dto.getLanguage() != null) {
            book.setLanguage(dto.getLanguage());
        }
        repository.save(book);
        return mapToDTO(book);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("Libro no encontrado");
        }
        repository.deleteById(id);
    }

    private BookResponseDTO mapToDTO(Book book) {
        return new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(),
                book.getPublicationYear(), book.getLanguage(), book.getPages(), book.getGenre());
    }
}
