package com.example.parcial2.domain.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateDTO {
    private String title;
    private String language;
}
