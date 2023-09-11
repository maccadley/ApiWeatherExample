package com.testCase.ApiSolution.Controllers;

import com.testCase.ApiSolution.model.News;
import com.testCase.ApiSolution.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    NewsService service;

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<News>> getAll() {
        List<News> result;
        result = service.getAllNews();
        return ResponseEntity.ok(result);
    }




}
