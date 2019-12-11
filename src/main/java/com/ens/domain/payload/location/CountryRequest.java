package com.ens.domain.payload.location;

import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CountryRequest {

    @NotNull
    private Set<String> countryNames;

}
