package com.ens.domain.payload.news;

import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsType;
import com.ens.domain.entity.news.VideoType;
import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest implements Serializable {

    @NotBlank
    private String headLine;
    protected String description;
    @NotBlank
    private String thumbnailImageUrl;
    private String videoUrl;
    private String youtubeVideoId;
    private String duration;
    private String size;
    private VideoType videoType;
    @NotNull
    private ContentType contentType;
    @NotNull
    private NewsType newsType;
    private boolean isInternational;
    private UUID countryId;
    private UUID stateId;
    private UUID districtId;
    private UUID areaId;
}
