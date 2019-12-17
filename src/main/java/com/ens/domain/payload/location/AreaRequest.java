package com.ens.domain.payload.location;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class AreaRequest {

    @NotNull
    private Set<String> areaNames;
    @NotNull
    private Long countryId;
    @NotNull
    private Long stateId;
    @NotNull
    private Long districtId;

}
