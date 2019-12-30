package com.ens.domain.payload.news;

import java.time.LocalDateTime;

public interface CommentResponse {

    Long getId();

    Long getNewsItemId();

    String getComment();

    String getCommentedByName();

    String getCommentedByProfileImageUrl();

    LocalDateTime getCreatedOn();

}
