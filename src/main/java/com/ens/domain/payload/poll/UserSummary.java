package com.ens.domain.payload.poll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary implements Serializable{

    private Long id;
    private String username;
    private String email;
    private String profileImageUrl;

}
