package com.ens.domain.payload.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemLocation implements Serializable {

    protected boolean isInternational;
    protected Long countryId;
    protected Long stateId;
    protected Long districtId;
    protected Long areaId;
}
