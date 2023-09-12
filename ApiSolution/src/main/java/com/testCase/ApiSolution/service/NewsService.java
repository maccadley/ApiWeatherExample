package com.testCase.ApiSolution.service;

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

    public List<News> getAllNews() {
        return newsMultiThreadFetching.fetchNewsInParallel();
    }

    public void saveToDb(List<News> news) {
        saveArticlesService.saveArticles(news);
    }

    public void saveBlockList(String blockWord) {
        blackListService.addBan(blockWord);
    }

}
