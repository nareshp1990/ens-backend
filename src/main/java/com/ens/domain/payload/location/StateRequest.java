package com.ens.domain.payload.location;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class StateRequest {

    @NotNull
    private Set<String> stateNames;
    @NotNull
    private Long countryId;

}
