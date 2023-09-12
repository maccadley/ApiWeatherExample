package com.testCase.ApiSolution.repositories.interfaces;

import com.testCase.ApiSolution.dbo.ArticleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleDTO, Long> {
}
