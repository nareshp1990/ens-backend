package com.ens.domain.entity.news;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID newsItemId;
    private String headLine;
    private String description;
    private String imageUrl;
    private ContentType contentType;
    private NewsType newsType;

    private UUID liked;
    private UUID disliked;

    private String thumbnailImageUrl;
    private String videoUrl;
    private String youtubeVideoId;
    private String duration;
    private String size;
    private VideoType videoType;

    private long views;
    private long likes;
    private long unLikes;
    private long comments;
    private long whatsAppShares;
    private long facebookShares;
    private long instagramShares;
    private long helloAppShares;
    private long twitterShares;
    private long telegramShares;
    
    private LocalDateTime createdOn;
    private String createdBy;
    private String createdByProfileImageUrl;

    public NewsItemResponse(UUID newsItemId, String headLine, String description,
            String imageUrl, ContentType contentType, NewsType newsType, UUID liked,
            UUID disliked, String thumbnailImageUrl, String videoUrl, String youtubeVideoId,
            String duration, String size, VideoType videoType, long views, long likes, long unLikes,
            long comments, long whatsAppShares, long facebookShares, long instagramShares,
            long helloAppShares, long twitterShares, long telegramShares,
            LocalDateTime createdOn, String createdBy, String createdByProfileImageUrl) {
        this.newsItemId = newsItemId;
        this.headLine = headLine;
        this.description = description;
        this.imageUrl = imageUrl;
        this.contentType = contentType;
        this.newsType = newsType;
        this.liked = liked;
        this.disliked = disliked;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.videoUrl = videoUrl;
        this.youtubeVideoId = youtubeVideoId;
        this.duration = duration;
        this.size = size;
        this.videoType = videoType;
        this.views = views;
        this.likes = likes;
        this.unLikes = unLikes;
        this.comments = comments;
        this.whatsAppShares = whatsAppShares;
        this.facebookShares = facebookShares;
        this.instagramShares = instagramShares;
        this.helloAppShares = helloAppShares;
        this.twitterShares = twitterShares;
        this.telegramShares = telegramShares;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.createdByProfileImageUrl = createdByProfileImageUrl;
    }
}
