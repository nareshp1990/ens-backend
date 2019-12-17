package com.ens.domain.entity.user;

import com.ens.domain.entity.audit.DateAudit;
import com.ens.domain.payload.user.UserViews.Internal;
import com.ens.domain.payload.user.UserViews.Public;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Setter
@Getter
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "mobile_number" })
})
@Entity
public class User extends DateAudit {

    @JsonView(Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(Public.class)
    @Column(name = "user_name")
    private String userName;

    @JsonView(Public.class)
    @Column(name = "first_name")
    private String firstName;

    @JsonView(Public.class)
    @Column(name = "last_name")
    private String lastName;

    @JsonView(Internal.class)
    @Column(name = "password")
    private String password;

    @JsonView(Public.class)
    @Email
    @Column(name = "email")
    private String email;

    @JsonView(Public.class)
    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonView(Public.class)
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @JsonView(Internal.class)
    @Column(name = "fcm_registration_key")
    private String fcmRegistrationKey;

    @JsonView(Public.class)
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserProfile userProfile;

}
