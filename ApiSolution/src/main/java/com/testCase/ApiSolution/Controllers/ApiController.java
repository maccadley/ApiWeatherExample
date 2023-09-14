package com.testCase.ApiSolution.Controllers;

import com.testCase.ApiSolution.dbo.ArticleDTO;
import com.testCase.ApiSolution.model.News;
import com.testCase.ApiSolution.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    NewsService service;

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<ArticleDTO>> getAll() {
        List<ArticleDTO> result;
        result = service.getAllNews();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addblock")
    @ResponseBody
    public ResponseEntity addBlockWork(@RequestParam("block") String wordToBan) {
        service.saveBlockList(wordToBan);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/removeblock")
    @ResponseBody
    public ResponseEntity removeFromBlock(@RequestParam("unblock") String wordToBan) {
        service.removeFromBlockList(wordToBan);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/id")
    @ResponseBody
    public ResponseEntity<ArticleDTO> getArticleById(@RequestParam("id") Long id) {
        ArticleDTO result = service.getArticleById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
    @GetMapping("/newssite")
    @ResponseBody
    public ResponseEntity<List<ArticleDTO>> getArticlesByNewsSite(@RequestParam("newssite") String newsSite) {
        List<ArticleDTO> result = service.getArticlesByNewsSite(newsSite);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }


}
