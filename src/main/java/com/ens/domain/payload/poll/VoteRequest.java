package com.ens.domain.payload.poll;

import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest implements Serializable{

    @NotNull
    private UUID choiceId;

}
