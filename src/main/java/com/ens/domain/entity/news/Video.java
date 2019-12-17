package com.ens.domain.entity.news;

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
@Table(name = "news_item_video")
@Entity
@NoArgsConstructor
public class Video extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private NewsItem newsItem;

    @Column(name = "thumbnail_image_url",nullable = false)
    private String thumbnailImageUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "youtube_video_id")
    private String youtubeVideoId;

    @Column(name = "duration")
    private String duration;

    @Column(name = "size")
    private String size;

    @Enumerated(EnumType.STRING)
    @Column(name = "video_type",nullable = false)
    private VideoType videoType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

}
