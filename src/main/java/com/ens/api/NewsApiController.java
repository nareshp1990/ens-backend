package com.ens.api;

import com.ens.domain.entity.news.ActionType;
import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemActionResponse;
import com.ens.domain.entity.news.NewsItemResponse;
import com.ens.domain.payload.ApiResponse;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.news.CommentResponse;
import com.ens.domain.payload.news.NewsItemRequest;
import com.ens.domain.payload.news.ScrollResponse;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.news.NewsItemService;
import com.ens.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import java.util.Set;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "news service", description = "The news service API", tags = {"news"})
@RestController
@RequestMapping("/v1/api/news")
@Slf4j
public class NewsApiController {

    @Autowired
    private NewsItemService newsItemService;

    @ApiOperation(value = "create news", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = ApiResponse.class)})
    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse> createNews(@PathVariable Long userId, @Valid @RequestBody NewsItemRequest newsRequest) {
        NewsItem newsItem = newsItemService.createNews(userId, newsRequest);
        return ResponseEntity.ok(new ApiResponse(newsItem.getId(),true,"Created Successfully"));
    }

    @ApiOperation(value = "create video news", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = ApiResponse.class)})
    @PostMapping("/{userId}/video")
    public ResponseEntity<ApiResponse> createVideo(@PathVariable Long userId, @Valid @RequestBody VideoRequest videoRequest) {
        NewsItem newsItem = newsItemService.createVideo(userId, videoRequest);
        return ResponseEntity.ok(new ApiResponse(newsItem.getId(),true,"Created Successfully"));
    }

    @ApiOperation(value = "post comment on news", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = ApiResponse.class)})
    @PostMapping("/{userId}/{newsItemId}/comment")
    public ResponseEntity<ApiResponse> postComment(@PathVariable Long userId, @PathVariable Long newsItemId, @RequestParam String comment) {
        newsItemService.postComment(userId,newsItemId,comment);
        return ResponseEntity.ok(new ApiResponse(true,"Updated Successfully"));
    }

    @ApiOperation(value = "post user action on news", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = NewsItemActionResponse.class)})
    @PostMapping("/{userId}/{newsItemId}/action")
    public NewsItemActionResponse postNewsItemAction(@PathVariable Long userId, @PathVariable Long newsItemId, @RequestParam ActionType actionType) {
        newsItemService.postNewsItemAction(userId, newsItemId, actionType);
        return newsItemService.getNewsItemAction(newsItemId);
    }

    @ApiOperation(value = "fetch all news items", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = NewsItemResponse.class)})
    @GetMapping("/{userId}")
    public PagedResponse<NewsItemResponse> getAllNewsItems(@PathVariable Long userId,
            @RequestParam Set<ContentType> contentTypes,
            @RequestParam Long newsItemId,
            @RequestParam(value = "visible", defaultValue = "true") Boolean visible,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return newsItemService.getNewsItems(userId, contentTypes, newsItemId, visible, page, size);
    }

    @ApiOperation(value = "delete news", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = ApiResponse.class)})
    @DeleteMapping("/{newsItemId}")
    public ResponseEntity<ApiResponse> deleteNewsItem(@PathVariable Long newsItemId){
        newsItemService.delete(newsItemId);
        return ResponseEntity.ok(new ApiResponse(true, "Deleted Successfully"));
    }
    @GetMapping(value = "/{userId}/scroll")
    public ScrollResponse getScrollText(@PathVariable Long userId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return newsItemService.getNewsScrollText(userId,page,size);
    }

    @ApiOperation(value = "get user actions on news", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = NewsItemActionResponse.class)})
    @GetMapping("/{userId}/{newsItemId}/action")
    public NewsItemActionResponse getNewsItemUserActions(@PathVariable Long userId, @PathVariable Long newsItemId) {
        return newsItemService.getNewsItemAction(newsItemId);
    }

    @ApiOperation(value = "fetch news item by id", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = NewsItemResponse.class)})
    @GetMapping("/{userId}/{newsItemId}")
    public NewsItemResponse getNewsItemById(@PathVariable Long userId, @PathVariable Long newsItemId){
        return newsItemService.getNewsItemById(userId, newsItemId);
    }

    @ApiOperation(value = "approve news item", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = ApiResponse.class)})
    @PostMapping("/{userId}/{newsItemId}/approve")
    public ResponseEntity<ApiResponse> approveNewsItem(@PathVariable Long userId, @PathVariable Long newsItemId) {
        newsItemService.approveNewsItem(userId,newsItemId);
        return ResponseEntity.ok(new ApiResponse(true,"Updated Successfully"));
    }

    @ApiOperation(value = "fetch all comments", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "comment response", response = PagedResponse.class)})
    @GetMapping("/{newsItemId}/comments")
    public ResponseEntity<PagedResponse<CommentResponse>> getAllComments(@PathVariable Long newsItemId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return ResponseEntity.ok(newsItemService.getAllComments(newsItemId,page,size));
    }


}
