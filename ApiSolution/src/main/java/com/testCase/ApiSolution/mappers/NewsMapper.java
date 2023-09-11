package com.testCase.ApiSolution.mappers;

import com.testCase.ApiSolution.model.News;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NewsMapper implements RowMapper<News> {
    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();
        news.setId(rs.getInt("id"));
        news.setTitle(rs.getString("title"));
        news.setUrl(rs.getString("url"));
        news.setImageUrl(rs.getString("imageUrl"));
        news.setNewsSite(rs.getString("newsSite"));
        news.setSummary(rs.getString("summary"));
        news.setPublishedAt(rs.getString("publishedAt"));
        news.setUpdatedAt(rs.getString("updatedAt"));
        news.setFeatured(rs.getBoolean("featured"));
        //news.setLaunches();
        return null;
    }
}
