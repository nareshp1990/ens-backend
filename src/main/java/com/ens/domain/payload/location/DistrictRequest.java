package com.ens.domain.payload.location;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class DistrictRequest {

    @NotNull
    private Set<String> districtNames;
    @NotNull
    private Long countryId;
    @NotNull
    private Long stateId;

}
