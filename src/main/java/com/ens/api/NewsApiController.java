package com.ens.api;

import com.ens.domain.entity.news.ActionType;
import com.ens.domain.payload.news.NewsRequest;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.service.news.NewsItemService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createNews(@PathVariable UUID userId, @Valid @RequestBody NewsRequest newsRequest) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/video")
    public ResponseEntity createVideo(@PathVariable UUID userId, @Valid @RequestBody VideoRequest videoRequest) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/{newsItemId}/comment")
    public ResponseEntity postComment(@PathVariable UUID userId, @PathVariable UUID newsItemId, @RequestParam String comment) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/{newsItemId}/action")
    public ResponseEntity postNewsItemAction(@PathVariable UUID userId, @PathVariable UUID newsItemId, @RequestParam ActionType actionType) {

        return ResponseEntity.ok().build();
    }
}
