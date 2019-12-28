package com.ens.service.news;

import com.ens.domain.entity.news.ActionType;
import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemActionResponse;
import com.ens.domain.entity.news.NewsItemResponse;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.news.NewsItemRequest;
import com.ens.domain.payload.news.ScrollResponse;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.IService;
import java.util.Optional;
import java.util.Set;

public interface NewsItemService extends IService<NewsItem> {

    NewsItem createNews(Long userId, NewsItemRequest newsItemRequest);

    NewsItem createVideo(Long userId, VideoRequest videoRequest);

    void postComment(Long userId, Long newsItemId, String comment);

    void postNewsItemAction(Long userId, Long newsItemId, ActionType actionType);

    NewsItemActionResponse getNewsItemAction(Long newsItemId);

    PagedResponse<NewsItemResponse> getNewsItems(Long userId, Set<ContentType> contentTypes, Long newsItemId, Boolean visible, int page, int size);

    ScrollResponse getNewsScrollText(Long userId, int page, int size);

    NewsItemResponse getNewsItemById(Long userId, Long newsItemId);
}
