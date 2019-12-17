package com.ens.domain.entity.news;

import com.ens.domain.entity.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@Table(name = "news_item_social_shares")
@Entity
@NoArgsConstructor
public class NewsItemSocialShare extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private NewsItem newsItem;

    @Column(name = "views")
    private long views;

    @Column(name = "whats_app_shares")
    private long whatsAppShares;

    @Column(name = "facebook_shares")
    private long facebookShares;

    @Column(name = "twitter_shares")
    private long twitterShares;

    @Column(name = "instagram_shares")
    private long instagramShares;

    @Column(name = "hello_app_shares")
    private long helloAppShares;

    @Column(name = "telegram_shares")
    private long telegramShares;

    public NewsItemSocialShare(NewsItem newsItem) {
        this.newsItem = newsItem;
    }
}
