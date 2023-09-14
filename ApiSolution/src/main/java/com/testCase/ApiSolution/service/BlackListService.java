package com.testCase.ApiSolution.service;

import com.testCase.ApiSolution.dbo.ArticleDTO;
import com.testCase.ApiSolution.dbo.BlackList;
import com.testCase.ApiSolution.model.News;
import com.testCase.ApiSolution.repositories.interfaces.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlackListService {

    private final BlackListRepository blackListRepository;

    @Autowired
    public BlackListService(BlackListRepository blackListRepository) {
        this.blackListRepository = blackListRepository;
    }

    public List<String> getAllValuesFromBlackList() {
        List<BlackList> blackList = blackListRepository.findAll();
        return blackList.stream()
                .map(BlackList::getValue)
                .collect(Collectors.toList());
    }

    public void addBan(String ban) {
        BlackList banWord = new BlackList();
        banWord.setValue(ban);
        blackListRepository.save(banWord);
    }

    public void removeBan(String banToRemove) {
        BlackList banWord = new BlackList();
        banWord.setValue(banToRemove);
        blackListRepository.delete(banWord);
    }

    public List<ArticleDTO> getArticlesFilteredByBlackList(List<ArticleDTO> unfilteredList) {
        List<ArticleDTO> result = new ArrayList<>();
        List<String> blackList = getAllValuesFromBlackList();
        for (ArticleDTO article : unfilteredList) {
            String title = article.getTitle();
            if (title != null && !containsAny(title, blackList)) {
                result.add(article);
            }
        }
        return result;
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
