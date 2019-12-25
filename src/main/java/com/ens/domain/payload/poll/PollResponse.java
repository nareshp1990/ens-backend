package com.ens.domain.payload.poll;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollResponse implements Serializable{

    private Long id;
    private String question;
    private List<ChoiceResponse> choices;
    private UserSummary createdBy;
    private LocalDateTime creationDateTime;
    private LocalDateTime expirationDateTime;
    private Boolean isExpired;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedChoice;
    private Long totalVotes;


}
