package com.ens.domain.entity.news;

import com.ens.domain.entity.audit.DateAudit;
import com.ens.domain.entity.location.Area;
import com.ens.domain.entity.location.Country;
import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import com.ens.domain.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@Table(name = "news_item_location")
@Entity
@NoArgsConstructor
public class NewsItemLocation extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private NewsItem newsItem;

    @Column(name = "is_international")
    private boolean isInternational;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id",nullable = true)
    @JsonIgnore
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id",nullable = true)
    @JsonIgnore
    private State state;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id",nullable = true)
    @JsonIgnore
    private District district;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id",nullable = true)
    @JsonIgnore
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

}
