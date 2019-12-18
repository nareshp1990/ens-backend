package com.ens.repo.news;

import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemActionResponse;
import com.ens.domain.entity.news.NewsItemResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsItemRepository extends JpaRepository<NewsItem, Long>, NewsItemRepositoryCustom {

    @Query(value = "select NEW com.ens.domain.entity.news.NewsItemActionResponse( sh.views, count(ul.id) likes, count(unl.id) unLikes, count(uc.id) comments, sh.whats_app_shares whatsAppShares, sh.facebook_shares facebookShares,  "
            + " sh.instagram_shares instagramShares, sh.hello_app_shares helloAppShares, sh.twitter_shares twitterShares, sh.telegram_shares telegramShares, ni.id newsItemId ) from news_item ni "
            + " LEFT JOIN news_item_social_shares sh ON sh.news_item_id = ni.id "
            + " LEFT JOIN news_item_user_comments uc on uc.news_item_id = ni.id "
            + " LEFT JOIN news_item_user_likes ul on ul.news_item_id = ni.id "
            + " LEFT JOIN news_item_user_un_likes unl on unl.news_item_id = ni.id "
            + " LEFT JOIN news_item_video v on v.news_item_id = ni.id "
            + " WHERE ni.id = :newsItemId "
            + " GROUP BY ni.id ", nativeQuery = true)
    Optional<NewsItemActionResponse> getNewsItemActionResponseByNewsItemId(@Param("newsItemId") Long newsItemId);

    @Query(value =
            "select ni.id newsItemId, ni.head_line headLine, ni.description description, ni.image_url imageUrl, ni.content_type contentType, "
                    + "ni.news_type newsType, v.thumbnail_image_url thumbnailImageUrl, v.video_url videoUrl, v.youtube_video_id youtubeVideoId, v.duration duration, v.size size, v.video_type videoType, "
                    + "sh.views views, count(ul.id) likes, count(unl.id) unLikes, count(uc.id) comments, "
                    + "sh.whats_app_shares whatsAppShares, sh.facebook_shares facebookShares, sh.instagram_shares instagramShares, sh.hello_app_shares helloAppShares, sh.twitter_shares twitterShares, sh.telegram_shares telegramShares, "
                    + "ni.created_at createdOn, u.user_name createdBy, u.profile_image_url createdByProfileImageUrl from news_item ni "
                    + "LEFT JOIN news_item_social_shares sh ON sh.news_item_id = ni.id "
                    + "LEFT JOIN news_item_user_comments uc on uc.news_item_id = ni.id "
                    + "LEFT JOIN news_item_user_likes ul on ul.news_item_id = ni.id "
                    + "LEFT JOIN news_item_user_un_likes unl on unl.news_item_id = ni.id "
                    + "LEFT JOIN news_item_video v on v.news_item_id = ni.id "
                    + "LEFT JOIN user u on u.id = ni.user_id "
                    + "WHERE ni.content_type = :contentType "
                    + "GROUP BY ni.id ", nativeQuery = true,
            countQuery = "select count(ni.id) from news_item ni "
                    + "LEFT JOIN news_item_social_shares sh ON sh.news_item_id = ni.id "
                    + "LEFT JOIN news_item_user_comments uc on uc.news_item_id = ni.id "
                    + "LEFT JOIN news_item_user_likes ul on ul.news_item_id = ni.id "
                    + "LEFT JOIN news_item_user_un_likes unl on unl.news_item_id = ni.id "
                    + "LEFT JOIN news_item_video v on v.news_item_id = ni.id "
                    + "LEFT JOIN user u on u.id = ni.user_id "
                    + "WHERE ni.content_type = :contentType "
                    + "GROUP BY ni.id ")
    Page<NewsItemResponse> getAllNewsItems(@Param("contentType") String contentType, Pageable pageable);
}
