package com.ens.domain.payload.poll;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollResponse implements Serializable{

    private UUID id;
    private String question;
    private List<ChoiceResponse> choices;
    private UserSummary createdBy;
    private LocalDateTime creationDateTime;
    private Instant expirationDateTime;
    private Boolean isExpired;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID selectedChoice;
    private Long totalVotes;


}
