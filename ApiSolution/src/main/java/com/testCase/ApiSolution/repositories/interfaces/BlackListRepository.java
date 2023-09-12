package com.testCase.ApiSolution.repositories.interfaces;

import com.testCase.ApiSolution.dbo.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
}
