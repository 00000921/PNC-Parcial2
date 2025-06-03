package com.example.parcial2.controller;

import com.example.parcial2.domain.dto.request.BookCreateDTO;
import com.example.parcial2.domain.dto.request.BookUpdateDTO;
import com.example.parcial2.domain.dto.response.BookResponseDTO;
import com.example.parcial2.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody BookCreateDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll(@RequestParam(required = false) String author,
                                                        @RequestParam(required = false) String language,
                                                        @RequestParam(required = false) String genre,
                                                        @RequestParam(required = false) Integer minPages,
                                                        @RequestParam(required = false) Integer maxPages) {
        return ResponseEntity.ok(service.getAll(author, language, genre, minPages, maxPages));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDTO> getByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(service.getByIsbn(isbn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody BookUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
