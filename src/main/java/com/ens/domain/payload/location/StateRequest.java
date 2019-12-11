package com.ens.domain.payload.location;

import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StateRequest {

    @NotNull
    private Set<String> stateNames;
    @NotNull
    private UUID countryId;

}
