package com.ens.domain.payload.news;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrollResponse implements Serializable {

    private String scrollText;

}
