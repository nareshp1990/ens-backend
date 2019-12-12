package com.ens.repo.news;

import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemActionResponse;
import com.ens.domain.entity.news.NewsItemResponse;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsItemRepository extends JpaRepository<NewsItem, UUID> {

    @Query("SELECT NEW com.ens.domain.entity.news.NewsItemActionResponse( sh.views, count(ul.id), count(unl.id), count(uc.id), "
    + " sh.whatsAppShares, sh.facebookShares, sh.instagramShares, sh.helloAppShares, sh.twitterShares, sh.telegramShares, ni.id ) from NewsItem ni"
    + " JOIN  NewsItemSocialShare sh ON sh.newsItem.id = ni.id"
    + " JOIN  UserComment uc ON uc.newsItem.id = ni.id"
    + " JOIN  UserUnLike unl ON unl.newsItem.id = ni.id"
    + " JOIN  UserLike ul ON ul.newsItem.id = ni.id WHERE ni.id = :newsItemId")
    Optional<NewsItemActionResponse> getNewsItemActionResponseByNewsItemId(@Param("newsItemId") UUID newsItemId);

    @Query("SELECT NEW com.ens.domain.entity.news.NewsItemResponse( ni.id, ni.headLine, ni.description, ni.imageUrl, ni.contentType,"
            + " ni.newsType, vul.user.id, vunl.user.id, v.thumbnailImageUrl, v.videoUrl, v.youtubeVideoId, v.duration, v.size, v.videoType,"
            + " sh.views, count(ul.id), count(unl.id), count(uc.id), "
            + " sh.whatsAppShares, sh.facebookShares, sh.instagramShares, sh.helloAppShares, sh.twitterShares, sh.telegramShares,"
            + " ni.createdAt, u.userName, u.profileImageUrl ) from NewsItem ni"
            + " JOIN  NewsItemSocialShare sh ON sh.newsItem.id = ni.id"
            + " JOIN  UserComment uc ON uc.newsItem.id = ni.id"
            + " JOIN  UserUnLike unl ON unl.newsItem.id = ni.id"
            + " JOIN  UserLike ul ON ul.newsItem.id = ni.id"
            + " JOIN  UserUnLike vunl ON vunl.newsItem.id = ni.id AND vunl.user.id = :userId"
            + " JOIN  UserLike vul ON vul.newsItem.id = ni.id AND vul.user.id = :userId"
            + " JOIN  Video v ON v.newsItem.id = ni.id"
            + " JOIN  User u ON u.id = ni.user.id WHERE ni.contentType = :contentType GROUP BY ni.id")
    Page<NewsItemResponse> getAllNewsItems(@Param("userId") UUID userId, @Param("contentType") ContentType contentType, Pageable pageable);
}
