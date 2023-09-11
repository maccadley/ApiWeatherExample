package com.testCase.ApiSolution.repositories;

import com.testCase.ApiSolution.model.News;
import com.testCase.ApiSolution.repositories.interfaces.NewsGathering;
import com.testCase.ApiSolution.service.NewsCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsGatheringImpl implements NewsGathering {

    @Autowired
    NewsCollector collector;

    @Override
    public List<News> getAll() {
        return collector.getAll();
    }
}
