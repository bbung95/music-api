package com.bbung.musicapi.domain.genre.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreListDto {

    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
