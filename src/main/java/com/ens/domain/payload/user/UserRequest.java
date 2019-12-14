package com.ens.domain.payload.user;

import com.ens.domain.entity.user.Gender;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private UUID countryId;
    private UUID stateId;
    private UUID districtId;
    private UUID areaId;


}
