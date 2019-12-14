package com.ens.domain.entity.news;

import com.ens.domain.entity.audit.DateAudit;
import com.ens.domain.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Table(name = "news_item")
@Entity
@NoArgsConstructor
public class NewsItem extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "head_line",nullable = false)
    private String headLine;

    @Lob
    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "image_url",nullable = true, columnDefinition = "varchar(255) default NULL")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type",nullable = false)
    private ContentType contentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "news_type",nullable = false)
    private NewsType newsType;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "newsItem")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Video video;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "newsItem")
    @JsonIgnore
    private List<UserLike> userLikes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "newsItem")
    @JsonIgnore
    private List<UserUnLike> userUnLikes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "newsItem")
    @JsonIgnore
    private List<UserComment> userComments = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "newsItem")
    @JsonIgnore
    private NewsItemSocialShare socialShare;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "newsItem")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private NewsItemLocation locationInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

}
