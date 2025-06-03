package com.example.parcial2.domain.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private String language;
    private Integer pages;
    private String genre;
}
