package com.ens.domain.payload.news;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemLocation implements Serializable {

    protected boolean isInternational;
    protected UUID countryId;
    protected UUID stateId;
    protected UUID districtId;
    protected UUID areaId;
}
