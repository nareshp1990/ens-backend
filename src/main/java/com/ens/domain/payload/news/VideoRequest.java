package com.ens.domain.payload.news;

import com.ens.domain.entity.news.VideoType;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest extends NewsItemCommon implements Serializable {

    @NotBlank
    private String thumbnailImageUrl;
    private String videoUrl;
    private String youtubeVideoId;
    private String duration;
    private String size;
    private VideoType videoType;

}
