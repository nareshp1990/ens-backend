package com.ens.service.news;

import com.ens.domain.entity.news.ActionType;
import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.payload.news.NewsRequest;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.IService;
import java.util.UUID;

public interface NewsItemService extends IService<NewsItem> {

    void createNews(UUID userId, NewsRequest newsRequest);

    void createVideo(UUID userId, VideoRequest videoRequest);

    void postComment(UUID userId, UUID newsItemId, String comment);

    void postNewsItemAction(UUID userId, UUID newsItemId, ActionType actionType);

    void getNewsItems(UUID userId, ContentType contentType, int page, int size);

}
