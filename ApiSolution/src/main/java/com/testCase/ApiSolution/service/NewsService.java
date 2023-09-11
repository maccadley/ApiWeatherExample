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

    public List<News> getAllNews() {
        return gathering.getAll();
    }

}
