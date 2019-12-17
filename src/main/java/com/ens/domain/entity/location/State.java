package com.ens.domain.entity.location;

import com.ens.domain.entity.audit.DateAudit;
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
@Table(name = "states", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"state_name"})
})
@Entity
@NoArgsConstructor
public class State extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Country country;

    @Column(name = "state_name",nullable = false)
    private String stateName;

    @Column(name = "capital_name")
    private String capitalName;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public State(Country country, String stateName, User user) {
        this.country = country;
        this.stateName = stateName;
        this.user = user;
    }
}
