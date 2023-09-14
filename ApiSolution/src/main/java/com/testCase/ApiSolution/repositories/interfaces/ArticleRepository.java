package com.testCase.ApiSolution.repositories.interfaces;

import com.testCase.ApiSolution.dbo.ArticleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleDTO, Long> {

    List<ArticleDTO> findByNewsSite(String newsSite);
}
