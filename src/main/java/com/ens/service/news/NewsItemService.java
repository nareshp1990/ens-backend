package com.ens.service.news;

import com.ens.domain.entity.news.ActionType;
import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemActionResponse;
import com.ens.domain.entity.news.NewsItemResponse;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.news.NewsItemRequest;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.IService;
import java.util.UUID;

public interface NewsItemService extends IService<NewsItem> {

    NewsItem createNews(UUID userId, NewsItemRequest newsItemRequest);

    NewsItem createVideo(UUID userId, VideoRequest videoRequest);

    void postComment(UUID userId, UUID newsItemId, String comment);

    NewsItemActionResponse postNewsItemAction(UUID userId, UUID newsItemId, ActionType actionType);

    PagedResponse<NewsItemResponse> getNewsItems(UUID userId, ContentType contentType, int page, int size);

}
