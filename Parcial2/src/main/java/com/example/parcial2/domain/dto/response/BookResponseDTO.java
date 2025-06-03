package com.example.parcial2.domain.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private String language;
    private Integer pages;
    private String genre;
}
