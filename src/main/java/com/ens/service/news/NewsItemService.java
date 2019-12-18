package com.ens.service.news;

import com.ens.domain.entity.news.*;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.news.NewsItemRequest;
import com.ens.domain.payload.news.ScrollResponse;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.IService;

public interface NewsItemService extends IService<NewsItem> {

    NewsItem createNews(Long userId, NewsItemRequest newsItemRequest);

    NewsItem createVideo(Long userId, VideoRequest videoRequest);

    void postComment(Long userId, Long newsItemId, String comment);

    void postNewsItemAction(Long userId, Long newsItemId, ActionType actionType);

    NewsItemActionResponse getNewsItemAction(Long newsItemId);

    PagedResponse<NewsItemResponse> getNewsItems(Long userId, ContentType contentType, int page, int size);

    ScrollResponse getNewsScrollText(Long userId, int page, int size);

}
