package com.ens.domain.payload.news;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResponse implements Serializable {

    private Long id;
    private Long newsItemId;
    private String comment;
    private String commentedByProfileImageUrl;
    private String commentedByName;
    private LocalDateTime createdOn;

}
