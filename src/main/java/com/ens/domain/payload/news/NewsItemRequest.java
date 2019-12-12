package com.ens.domain.payload.news;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemRequest extends NewsItemCommon implements Serializable{

    @NotBlank
    private String imageUrl;

}
