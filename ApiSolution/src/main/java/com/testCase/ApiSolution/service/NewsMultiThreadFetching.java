package com.testCase.ApiSolution.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testCase.ApiSolution.Constants.ApiConstants;
import com.testCase.ApiSolution.model.News;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Log4j2
@Component
public class NewsMultiThreadFetching {

    @Value("${testCase.maximum.threads}")
    int threadsNumber = 5;

    List<String> blackList;

//    private ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);

    public List<News> fetchNewsInParallel() {
        List<News> result = new ArrayList<>();
        List<Callable<List<News>>> tasks = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);

        for (int i = 0; i < threadsNumber; i++) {
            tasks.add(() -> fetchNewsFromApi());
        }

        try {
            List<Future<List<News>>> futures = executorService.invokeAll(tasks);
            for (Future<List<News>> future : futures) {
                result.addAll(future.get());
            }
        } catch (InterruptedException e) {
            log.error("Process was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Other error occurred: " + e.getMessage());
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        } finally {
            executorService.shutdown();
        }

        return result;
    }


    private List<News> fetchNewsFromApi() {
        URL url;
        List<News> result = new ArrayList<>();
        try {
            url = new URL(ApiConstants.API_URL_ALL);
            HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode==HttpURLConnection.HTTP_OK) {
                ObjectMapper mapper = new ObjectMapper();
                News[] newsItems = mapper.readValue(connection.getInputStream(), News[].class);
                for (News news : newsItems) {
                    result.add(news);
                }

            } else if (responseCode==HttpURLConnection.HTTP_BAD_GATEWAY) {
                log.error("Server experiencing troubles");
            } else {
                log.error("Error with getting news, code: " + responseCode);
            }

        } catch (MalformedURLException exp) {
            log.error("An error with extracting URL: " + exp.getMessage());
        } catch (IOException exp) {
            log.error("An error with connection: " + exp.getMessage());
        }
        return sortByPublishedAt(result);
    }

    public List<News> sortByPublishedAt(List<News> newsList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return newsList.stream()
                .sorted(Comparator.comparing(news -> LocalDateTime.parse(news.getPublishedAt(), formatter)))
                .collect(Collectors.toList());
    }


}
