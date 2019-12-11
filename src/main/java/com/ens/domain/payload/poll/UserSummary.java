package com.ens.domain.payload.poll;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary implements Serializable{

    private UUID id;
    private String username;
    private String email;
    private String profileImageUrl;

}
