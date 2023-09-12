package com.testCase.ApiSolution.service;

import com.testCase.ApiSolution.dbo.ArticleDTO;
import com.testCase.ApiSolution.model.News;
import com.testCase.ApiSolution.repositories.interfaces.ArticleRepository;
import com.testCase.ApiSolution.repositories.interfaces.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveArticlesService {

    private final ArticleRepository articleRepository;

    private final BlackListService blackListService;

    @Autowired
    public SaveArticlesService(ArticleRepository articleRepository, BlackListService blackListService) {
        this.articleRepository = articleRepository;
        this.blackListService = blackListService;
    }

    public void saveArticles(List<News> newsList) {
        List<String> blackList = blackListService.getAllValuesFromBlackList();

        for (News news : newsList) {
            String summary = news.getSummary();
            if (summary != null && !containsAny(summary, blackList)) {
                ArticleDTO article = new ArticleDTO();
                article.setTitle(news.getTitle());
                article.setNewsSite(news.getNewsSite());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                article.setPublishedDate(LocalDateTime.parse(news.getPublishedAt(), formatter));
                article.setArticle(news.getSummary());
                articleRepository.save(article);
            }
        }
    }

    private boolean containsAny(String input, List<String> values) {
        for (String value : values) {
            if (input.contains(value)) {
                return true;
            }
        }
        return false;
    }


}
