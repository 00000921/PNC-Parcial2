package com.example.parcial2.service;

import com.example.parcial2.domain.dto.request.*;
        import com.example.parcial2.domain.dto.response.*;

        import java.util.List;

public interface BookService {
    BookResponseDTO create(BookCreateDTO dto);
    List<BookResponseDTO> getAll(String author, String language, String genre, Integer minPages, Integer maxPages);
    BookResponseDTO getByIsbn(String isbn);
    BookResponseDTO update(Long id, BookUpdateDTO dto);
    void delete(Long id);
}
