package com.ens.domain.entity.user;

import com.ens.domain.entity.audit.DateAudit;
import com.ens.domain.entity.location.Area;
import com.ens.domain.entity.location.Country;
import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import com.ens.domain.payload.user.UserViews.Public;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
@Setter
@Getter
public class UserProfile extends DateAudit {

    @JsonView(Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @JsonView(Public.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonView(Public.class)
    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @JsonView(Public.class)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Country country;

    @JsonView(Public.class)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private State state;

    @JsonView(Public.class)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private District district;

    @JsonView(Public.class)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Area area;

}
