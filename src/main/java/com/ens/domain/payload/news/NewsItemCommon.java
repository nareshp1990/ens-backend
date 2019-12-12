package com.ens.domain.payload.news;

import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsType;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemCommon extends NewsItemLocation implements Serializable {

    @NotBlank
    protected String headLine;
    protected String description;
    @NotNull
    protected ContentType contentType;
    @NotNull
    protected NewsType newsType;
}
