package com.ens.api;

import com.ens.domain.entity.news.*;
import com.ens.domain.payload.ApiResponse;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.news.NewsItemRequest;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.news.NewsItemService;
import com.ens.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return newsItemService.postNewsItemAction(userId, newsItemId, actionType);
    }

    @ApiOperation(value = "fetch all news items", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = NewsItemResponse.class)})
    @GetMapping("/{userId}")
    public PagedResponse<NewsItemResponse> getAllNewsItems(@PathVariable Long userId,@RequestParam ContentType contentType,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return newsItemService.getNewsItems(userId, contentType, page, size);
    }

    @ApiOperation(value = "delete news", tags = {"news"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "api response", response = ApiResponse.class)})
    @DeleteMapping("/{newsItemId}")
    public ResponseEntity<ApiResponse> deleteNewsItem(@PathVariable Long newsItemId){
        newsItemService.delete(newsItemId);
        return ResponseEntity.ok(new ApiResponse(true, "Deleted Successfully"));
    }
    @GetMapping("/{userId}/scroll")
    public String getScrollText(@PathVariable Long userId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return newsItemService.getNewsScrollText(userId,page,size);
    }

}
