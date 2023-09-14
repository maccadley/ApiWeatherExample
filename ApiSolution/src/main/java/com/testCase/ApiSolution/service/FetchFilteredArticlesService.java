package com.testCase.ApiSolution.service;

import com.testCase.ApiSolution.dbo.ArticleDTO;
import com.testCase.ApiSolution.repositories.interfaces.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class FetchFilteredArticlesService {

    private final ArticleRepository articleRepository;

    private final BlackListService blackListService;

    @Autowired
    public FetchFilteredArticlesService(ArticleRepository articleRepository, BlackListService blackListService) {
        this.articleRepository = articleRepository;
        this.blackListService = blackListService;
    }

    public List<ArticleDTO> fetchAllFilteredArticles() {
        return articleRepository.findAll();
    }

    public ArticleDTO fetchArticleById(Long id) {
        Optional<ArticleDTO> articleDTO = articleRepository.findById(id);
        if (articleDTO.isPresent()) {
            return articleDTO.get();
        } else {
            return null;
        }
    }

    public List<ArticleDTO> fetchArticlesByNewsSite(String newsStie) {
        List<ArticleDTO> result = new ArrayList<>();
        result.addAll(articleRepository.findByNewsSite(newsStie));
        result = blackListService.getArticlesFilteredByBlackList(result);
        return result;
    }
}
