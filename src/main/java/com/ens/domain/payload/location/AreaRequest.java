package com.ens.domain.payload.location;

import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AreaRequest {

    @NotNull
    private Set<String> areaNames;
    @NotNull
    private UUID countryId;
    @NotNull
    private UUID stateId;
    @NotNull
    private UUID districtId;

}
