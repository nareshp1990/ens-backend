package com.ens.domain.payload.poll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest implements Serializable{

    @NotNull
    private Long choiceId;

}
