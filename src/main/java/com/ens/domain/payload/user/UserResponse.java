package com.ens.domain.payload.user;

import com.ens.domain.entity.location.Area;
import com.ens.domain.entity.location.Country;
import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import com.ens.domain.entity.user.Gender;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private UUID id;
    private String userName;
    private String email;
    private String mobileNumber;
    private String profileImageUrl;
    private String fcmRegistrationKey;

    private Gender gender;
    private LocalDate dateOfBirth;
    private Country country;
    private State state;
    private District district;
    private Area area;
}
