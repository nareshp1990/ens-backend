package com.ens.domain.payload.poll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResponse implements Serializable{

    private Long id;
    private String text;
    private long voteCount;
    private double pollPercentage;

}
