package com.ens.repo.news;

import com.ens.domain.entity.news.NewsItemActionResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class NewsItemActionModelMapper implements RowMapper<NewsItemActionResponse> {

    @Override
    public NewsItemActionResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        NewsItemActionResponse response = new NewsItemActionResponse();

        response.setViews(resultSet.getLong("views"));
        response.setLikes(resultSet.getLong("likes"));
        response.setUnLikes(resultSet.getLong("unLikes"));
        response.setComments(resultSet.getLong("comments"));
        response.setWhatsAppShares(resultSet.getLong("whatsAppShares"));
        response.setFacebookShares(resultSet.getLong("facebookShares"));
        response.setInstagramShares(resultSet.getLong("instagramShares"));
        response.setHelloAppShares(resultSet.getLong("helloAppShares"));
        response.setTwitterShares(resultSet.getLong("twitterShares"));
        response.setTelegramShares(resultSet.getLong("telegramShares"));
        response.setNewsItemId(resultSet.getLong("newsItemId"));

        return response;
    }
}
