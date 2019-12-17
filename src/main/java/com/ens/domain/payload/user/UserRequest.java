package com.ens.domain.payload.user;

import com.ens.domain.entity.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable{

    @NotBlank
    private String userName;
    private String email;
    @NotBlank
    private String mobileNumber;
    @NotBlank
    private String profileImageUrl;
    private String fcmRegistrationKey;
    @NotBlank
    private String password;
    @NotNull
    @Valid
    private Gender gender;
    @Valid
    private LocalDate dateOfBirth;

    private Long countryId;
    private Long stateId;
    private Long districtId;
    private Long areaId;


}
