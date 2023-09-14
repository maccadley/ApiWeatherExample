package com.testCase.ApiSolution.service;

import com.testCase.ApiSolution.dbo.ArticleDTO;
import com.testCase.ApiSolution.model.News;
import com.testCase.ApiSolution.repositories.interfaces.NewsGathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsGathering gathering;

    @Autowired
    private SaveArticlesService saveArticlesService;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private NewsMultiThreadFetching newsMultiThreadFetching;

    @Autowired
    private FetchFilteredArticlesService fetchFilteredArticlesService;

    public List<ArticleDTO> getAllNews() {
        List<News> unfilteredArticles = newsMultiThreadFetching.fetchNewsInParallel();
        saveArticlesService.saveArticles(unfilteredArticles);
        return fetchFilteredArticlesService.fetchAllFilteredArticles();
    }

    public ArticleDTO getArticleById(Long id) {
        return fetchFilteredArticlesService.fetchArticleById(id);
    }

    public List<ArticleDTO> getArticlesByNewsSite(String newsSite) {
        return fetchFilteredArticlesService.fetchArticlesByNewsSite(newsSite);
    }

    public void saveBlockList(String blockWord) {
        blackListService.addBan(blockWord);
    }

    public void removeFromBlockList(String unblockWord) {
        blackListService.removeBan(unblockWord);
    }

}
