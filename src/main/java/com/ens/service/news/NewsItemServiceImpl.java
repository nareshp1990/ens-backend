package com.ens.service.news;

import com.ens.domain.entity.location.Area;
import com.ens.domain.entity.location.Country;
import com.ens.domain.entity.location.District;
import com.ens.domain.entity.location.State;
import com.ens.domain.entity.news.ActionType;
import com.ens.domain.entity.news.ContentType;
import com.ens.domain.entity.news.NewsItem;
import com.ens.domain.entity.news.NewsItemActionResponse;
import com.ens.domain.entity.news.NewsItemLocation;
import com.ens.domain.entity.news.NewsItemResponse;
import com.ens.domain.entity.news.NewsItemSocialShare;
import com.ens.domain.entity.news.UserComment;
import com.ens.domain.entity.news.UserLike;
import com.ens.domain.entity.news.UserUnLike;
import com.ens.domain.entity.news.Video;
import com.ens.domain.entity.user.User;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.news.NewsItemRequest;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public NewsItem createNews(UUID userId, NewsItemRequest newsRequest) {

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

        return newsItemRepository.save(newsItem);
    }

    @Override
    public NewsItem createVideo(UUID userId, VideoRequest videoRequest) {

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

        return newsItemRepository.save(newsItem);
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
    public NewsItemActionResponse postNewsItemAction(UUID userId, UUID newsItemId, ActionType actionType) {

        User user = validationService.validateUser(userId);
        NewsItem newsItem = validationService.validateNewsItem(newsItemId);

        switch (actionType){
            case LIKE:{

                Optional<UserUnLike> userUnLikeOptional = userUnLikeRepository.findByNewsItemIdAndUserId(newsItemId, userId);
                userUnLikeOptional.ifPresent(userUnLike -> userUnLikeRepository.delete(userUnLike));

                UserLike userLike = userLikeRepository.findByNewsItemIdAndUserId(newsItemId,userId).orElseGet(() -> new UserLike(newsItem,user));

                userLikeRepository.save(userLike);

            }break;
            case UNLIKE:{

                Optional<UserLike> userLikeOptional = userLikeRepository.findByNewsItemIdAndUserId(newsItemId, userId);
                userLikeOptional.ifPresent(userLike -> userLikeRepository.delete(userLike));

                UserUnLike userUnLike = userUnLikeRepository.findByNewsItemIdAndUserId(newsItemId,userId).orElseGet(() -> new UserUnLike(newsItem,user));

                userUnLikeRepository.save(userUnLike);

            }break;
            case VIEW:
            case WHATSAPP:
            case FACEBOOK:
            case TWITTER:
            case INSTAGRAM:
            case HELLO_APP:
            case TELEGRAM:{

                updateNewsItemActions(actionType,newsItem);

            }break;

        }

        return newsItemRepository.getNewsItemActionResponseByNewsItemId(newsItemId).orElseGet(NewsItemActionResponse::new);
    }

    private void updateNewsItemActions(ActionType actionType,NewsItem newsItem){

        NewsItemSocialShare newsItemSocialShare = newsItemSocialShareRepository.findByNewsItemId(newsItem.getId()).orElseGet(()->new NewsItemSocialShare(newsItem));

        switch (actionType){
            case VIEW:{
                newsItemSocialShare.setViews(newsItemSocialShare.getViews()+1);
            }break;
            case WHATSAPP:{
                newsItemSocialShare.setWhatsAppShares(newsItemSocialShare.getWhatsAppShares()+1);
            }break;
            case TWITTER:{
                newsItemSocialShare.setTwitterShares(newsItemSocialShare.getTwitterShares()+1);
            }break;
            case FACEBOOK:{
                newsItemSocialShare.setFacebookShares(newsItemSocialShare.getFacebookShares()+1);
            }break;
            case TELEGRAM:{
                newsItemSocialShare.setTelegramShares(newsItemSocialShare.getTelegramShares()+1);
            }break;
            case HELLO_APP:{
                newsItemSocialShare.setHelloAppShares(newsItemSocialShare.getHelloAppShares()+1);
            }break;
            case INSTAGRAM:{
                newsItemSocialShare.setInstagramShares(newsItemSocialShare.getInstagramShares()+1);
            }break;
            default:break;
        }

        newsItemSocialShareRepository.save(newsItemSocialShare);

    }

    @Override
    public PagedResponse<NewsItemResponse> getNewsItems(UUID userId, ContentType contentType, int page, int size) {

        validationService.validatePageNumberAndSize(page,size);

        validationService.validateUser(userId);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<NewsItemResponse> newsItems = newsItemRepository.getAllNewsItems(userId,contentType, pageable);

        return new PagedResponse<>(newsItems.getContent(), newsItems.getNumber(),
                newsItems.getSize(), newsItems.getTotalElements(), newsItems.getTotalPages(), newsItems.isLast());
    }
}
