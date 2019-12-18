package com.ens.repo.news;

import com.ens.domain.entity.news.NewsItemActionResponse;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class NewsItemRepositoryCustomImpl implements NewsItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<NewsItemActionResponse> getNewsItemActionResponse(Long newsItemId) {

        String query = "select IFNULL(sh.views, 0) views, count(ul.id) likes, count(unl.id) unLikes, count(uc.id) comments, IFNULL(sh.whats_app_shares, 0) whatsAppShares, IFNULL(sh.facebook_shares, 0) facebookShares,  "
                + " IFNULL(sh.instagram_shares, 0) instagramShares, IFNULL(sh.hello_app_shares, 0) helloAppShares, IFNULL(sh.twitter_shares, 0) twitterShares, IFNULL(sh.telegram_shares, 0) telegramShares, ni.id newsItemId  from news_item ni "
                + " LEFT JOIN news_item_social_shares sh ON sh.news_item_id = ni.id "
                + " LEFT JOIN news_item_user_comments uc on uc.news_item_id = ni.id "
                + " LEFT JOIN news_item_user_likes ul on ul.news_item_id = ni.id "
                + " LEFT JOIN news_item_user_un_likes unl on unl.news_item_id = ni.id "
                + " LEFT JOIN news_item_video v on v.news_item_id = ni.id "
                + " WHERE ni.id = ? "
                + " GROUP BY ni.id ";

        NewsItemActionResponse response = jdbcTemplate
                .queryForObject(query, new Object[]{newsItemId}, new NewsItemActionModelMapper());

        return Optional.of(response);
    }
}
