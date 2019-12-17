package com.ens.domain.entity.poll;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceVoteCount {

    private UUID choiceId;
    private Long voteCount;

}
