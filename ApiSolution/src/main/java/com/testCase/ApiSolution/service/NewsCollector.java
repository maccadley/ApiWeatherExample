package com.testCase.ApiSolution.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testCase.ApiSolution.Constants.ApiConstants;
import com.testCase.ApiSolution.model.News;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class NewsCollector {


        URL url;

        public List<News> getAll() {
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
            return result;
        }

}
