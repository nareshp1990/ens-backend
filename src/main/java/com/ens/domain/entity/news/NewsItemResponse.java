package com.ens.domain.entity.news;

import java.time.LocalDateTime;

public interface NewsItemResponse {

    Long getNewsItemId();
    String getHeadLine();
    String getDescription();
    String getImageUrl();
    ContentType getContentType();
    NewsType getNewsType();

    String getThumbnailImageUrl();
    String getVideoUrl();
    String getYoutubeVideoId();
    String getDuration();
    String getSize();
    VideoType getVideoType();

    Long getViews();
    Long getLikes();
    Long getUnLikes();
    Long getComments();
    Long getWhatsAppShares();
    Long getFacebookShares();
    Long getInstagramShares();
    Long getHelloAppShares();
    Long getTwitterShares();
    Long getTelegramShares();

    LocalDateTime getCreatedOn();
    String getCreatedBy();
    String getCreatedByProfileImageUrl();

}
