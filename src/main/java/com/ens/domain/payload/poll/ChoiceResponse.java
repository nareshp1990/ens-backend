package com.ens.domain.payload.poll;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResponse implements Serializable{

    private UUID id;
    private String text;
    private long voteCount;

}
