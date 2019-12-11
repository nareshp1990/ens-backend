package com.ens.domain.payload.news;

import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsType;
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
public class NewsRequest implements Serializable{

    @NotBlank
    private String headLine;
    @NotBlank
    private String description;
    @NotBlank
    private String imageUrl;
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
