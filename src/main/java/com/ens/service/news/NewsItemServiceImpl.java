package com.ens.service.news;

import com.ens.domain.entity.location.Area;
import com.ens.domain.entity.location.Country;
import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import com.ens.domain.entity.news.ActionType;
import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemLocation;
import com.ens.domain.entity.news.UserComment;
import com.ens.domain.entity.news.UserLike;
import com.ens.domain.entity.news.UserUnLike;
import com.ens.domain.entity.news.Video;
import com.ens.domain.entity.user.User;
import com.ens.domain.payload.news.NewsRequest;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.repo.news.NewsItemRepository;
import com.ens.repo.news.NewsItemSocialShareRepository;
import com.ens.repo.news.UserCommentRepository;
import com.ens.repo.news.UserLikeRepository;
import com.ens.repo.news.UserUnLikeRepository;
import com.ens.service.ValidationService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsItemServiceImpl implements NewsItemService {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private UserCommentRepository userCommentRepository;

    @Autowired
    private UserLikeRepository userLikeRepository;

    @Autowired
    private UserUnLikeRepository userUnLikeRepository;

    @Autowired
    private NewsItemSocialShareRepository newsItemSocialShareRepository;

    @Override
    public NewsItem save(NewsItem newsItem) {
        return newsItemRepository.save(newsItem);
    }

    @Override
    public void delete(UUID id) {
        newsItemRepository.deleteById(id);
    }

    @Override
    public Optional<NewsItem> findOne(UUID id) {
        return newsItemRepository.findById(id);
    }

    @Override
    public Iterable<NewsItem> findAll() {
        return newsItemRepository.findAll();
    }

    @Override
    public Page<NewsItem> findAll(Pageable pgbl) {
        return newsItemRepository.findAll(pgbl);
    }

    @Override
    public void createNews(UUID userId, NewsRequest newsRequest) {

        User user = validationService.validateUser(userId);

        Country country = null;
        State state = null;
        District district = null;
        Area area = null;

        if (!newsRequest.isInternational()) {

            if (newsRequest.getCountryId() != null) {
                country = validationService.validateCountry(newsRequest.getCountryId());
            }

            if (newsRequest.getStateId() != null) {
                state = validationService.validateState(newsRequest.getStateId());
            }

            if (newsRequest.getDistrictId() != null) {
                district = validationService.validateDistrict(newsRequest.getDistrictId());
            }

            if (newsRequest.getAreaId() != null) {
                area = validationService.validateArea(newsRequest.getAreaId());
            }
        }

        NewsItem newsItem = new NewsItem();
        newsItem.setHeadLine(newsRequest.getHeadLine());
        newsItem.setDescription(newsRequest.getDescription());
        newsItem.setImageUrl(newsRequest.getImageUrl());
        newsItem.setContentType(newsRequest.getContentType());
        newsItem.setNewsType(newsRequest.getNewsType());
        newsItem.setUser(user);

        NewsItemLocation newsItemLocation = new NewsItemLocation();
        newsItemLocation.setArea(area);
        newsItemLocation.setDistrict(district);
        newsItemLocation.setState(state);
        newsItemLocation.setCountry(country);
        newsItemLocation.setInternational(newsRequest.isInternational());
        newsItemLocation.setNewsItem(newsItem);
        newsItemLocation.setUser(user);

        newsItem.setLocationInfo(newsItemLocation);

        newsItemRepository.save(newsItem);

    }

    @Override
    public void createVideo(UUID userId, VideoRequest videoRequest) {

        User user = validationService.validateUser(userId);

        Country country = null;
        State state = null;
        District district = null;
        Area area = null;

        if (!videoRequest.isInternational()) {

            if (videoRequest.getCountryId() != null) {
                country = validationService.validateCountry(videoRequest.getCountryId());
            }

            if (videoRequest.getStateId() != null) {
                state = validationService.validateState(videoRequest.getStateId());
            }

            if (videoRequest.getDistrictId() != null) {
                district = validationService.validateDistrict(videoRequest.getDistrictId());
            }

            if (videoRequest.getAreaId() != null) {
                area = validationService.validateArea(videoRequest.getAreaId());
            }
        }

        NewsItem newsItem = new NewsItem();
        newsItem.setHeadLine(videoRequest.getHeadLine());
        newsItem.setDescription(videoRequest.getDescription());
        newsItem.setContentType(videoRequest.getContentType());
        newsItem.setNewsType(videoRequest.getNewsType());
        newsItem.setUser(user);

        Video video = new Video();
        video.setThumbnailImageUrl(videoRequest.getThumbnailImageUrl());
        video.setDuration(videoRequest.getDuration());
        video.setSize(videoRequest.getSize());
        video.setVideoType(videoRequest.getVideoType());
        video.setVideoUrl(videoRequest.getVideoUrl());
        video.setYoutubeVideoId(videoRequest.getYoutubeVideoId());
        video.setUser(user);
        video.setNewsItem(newsItem);

        newsItem.setVideo(video);

        NewsItemLocation newsItemLocation = new NewsItemLocation();
        newsItemLocation.setArea(area);
        newsItemLocation.setDistrict(district);
        newsItemLocation.setState(state);
        newsItemLocation.setCountry(country);
        newsItemLocation.setInternational(videoRequest.isInternational());
        newsItemLocation.setNewsItem(newsItem);
        newsItemLocation.setUser(user);

        newsItem.setLocationInfo(newsItemLocation);

        newsItemRepository.save(newsItem);
    }

    @Override
    public void postComment(UUID userId, UUID newsItemId, String comment) {

        User user = validationService.validateUser(userId);
        NewsItem newsItem = validationService.validateNewsItem(newsItemId);

        UserComment userComment = new UserComment();
        userComment.setComment(comment);
        userComment.setNewsItem(newsItem);
        userComment.setUser(user);

        userCommentRepository.save(userComment);
    }

    @Override
    public void postNewsItemAction(UUID userId, UUID newsItemId, ActionType actionType) {

        User user = validationService.validateUser(userId);
        NewsItem newsItem = validationService.validateNewsItem(newsItemId);

        switch (actionType){
            case LIKE:{

                UserLike userLike = userLikeRepository.findByNewsItemIdAndUserId(newsItemId,userId).orElseGet(UserLike::new);

                userLike.setUser(user);
                userLike.setNewsItem(newsItem);

                userLikeRepository.save(userLike);

            }break;
            case UNLIKE:{

                UserUnLike userUnLike = userUnLikeRepository.findByNewsItemIdAndUserId(newsItemId,userId).orElseGet(UserUnLike::new);

                userUnLike.setUser(user);
                userUnLike.setNewsItem(newsItem);

                userUnLikeRepository.save(userUnLike);

            }break;
            case VIEW:{
            
            }break;
            case WHATSAPP:{
            }break;
            case TWITTER:{
            }break;
            case FACEBOOK:{
            }break;
            case TELEGRAM:{
            }break;
            case HELLO_APP:{
            }break;
            case INSTAGRAM:{
            }break;
        }

    }

    @Override
    public void getNewsItems(UUID userId, ContentType contentType, int page, int size) {

    }
}
