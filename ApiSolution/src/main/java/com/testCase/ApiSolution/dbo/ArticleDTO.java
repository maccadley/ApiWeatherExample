package com.testCase.ApiSolution.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Setter
@Getter
public class ArticleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String newsSite;
    private LocalDateTime publishedDate;

    @Lob
    private String article;


}
