package com.ens.domain.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Table(name = "news_item_social_shares")
@Entity
public class NewsItemSocialShare extends AuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_item_info_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NewsItemInfo newsItemInfo;

    @Column(name = "views")
    private int views;

    @Column(name = "whats_app_shares")
    private int whatsAppShares;

    @Column(name = "facebook_shares")
    private int facebookShares;

    @Column(name = "twitter_shares")
    private int twitterShares;

    @Column(name = "instagram_shares")
    private int instagramShares;

    @Column(name = "hello_app_shares")
    private int helloAppShares;

    @Column(name = "telegram_shares")
    private int telegramShares;

}
