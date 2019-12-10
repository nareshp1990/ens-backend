package com.ens.domain.entity.news;

import com.ens.domain.entity.audit.DateAudit;
import com.ens.domain.entity.user.User;
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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Table(name = "news_item")
@Entity
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

    @Column(name = "image_url",nullable = false)
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
    private Video video;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "newsItem")
    private List<UserLike> userLikes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "newsItem")
    private List<UserUnLike> userUnLikes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "newsItem")
    private List<UserComment> userComments = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "newsItem")
    private NewsItemSocialShare socialShare;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "newsItem")
    private NewsItemLocation locationInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
