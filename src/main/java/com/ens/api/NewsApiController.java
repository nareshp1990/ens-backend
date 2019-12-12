package com.ens.api;

import com.ens.domain.entity.news.ActionType;
import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemActionResponse;
import com.ens.domain.entity.news.NewsItemResponse;
import com.ens.domain.payload.ApiResponse;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.news.NewsItemRequest;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.news.NewsItemService;
import com.ens.util.AppConstants;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/news")
@Slf4j
public class NewsApiController {

    @Autowired
    private NewsItemService newsItemService;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse> createNews(@PathVariable UUID userId, @Valid @RequestBody NewsItemRequest newsRequest) {
        NewsItem newsItem = newsItemService.createNews(userId, newsRequest);
        return ResponseEntity.ok(new ApiResponse(newsItem.getId(),true,"Created Successfully"));
    }

    @PostMapping("/{userId}/video")
    public ResponseEntity<ApiResponse> createVideo(@PathVariable UUID userId, @Valid @RequestBody VideoRequest videoRequest) {
        NewsItem newsItem = newsItemService.createVideo(userId, videoRequest);
        return ResponseEntity.ok(new ApiResponse(newsItem.getId(),true,"Created Successfully"));
    }

    @PostMapping("/{userId}/{newsItemId}/comment")
    public ResponseEntity<ApiResponse> postComment(@PathVariable UUID userId, @PathVariable UUID newsItemId, @RequestParam String comment) {
        newsItemService.postComment(userId,newsItemId,comment);
        return ResponseEntity.ok(new ApiResponse(true,"Updated Successfully"));
    }

    @PostMapping("/{userId}/{newsItemId}/action")
    public NewsItemActionResponse postNewsItemAction(@PathVariable UUID userId, @PathVariable UUID newsItemId, @RequestParam ActionType actionType) {
        return newsItemService.postNewsItemAction(userId, newsItemId, actionType);
    }

    @GetMapping("/{userId}")
    public PagedResponse<NewsItemResponse> getAllNewsItems(@PathVariable UUID userId,@RequestParam ContentType contentType,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return newsItemService.getNewsItems(userId, contentType, page, size);
    }
}
