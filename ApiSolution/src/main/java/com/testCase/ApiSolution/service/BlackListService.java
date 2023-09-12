package com.testCase.ApiSolution.service;

import com.testCase.ApiSolution.dbo.BlackList;
import com.testCase.ApiSolution.repositories.interfaces.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
