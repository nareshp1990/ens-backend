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
import com.ens.domain.entity.user.UserType;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.fcm.PushNotificationRequest;
import com.ens.domain.payload.news.NewsItemRequest;
import com.ens.domain.payload.news.ScrollResponse;
import com.ens.domain.payload.news.VideoRequest;
import com.ens.exception.BadRequestException;
import com.ens.exception.ResourceNotFoundException;
import com.ens.repo.news.NewsItemRepository;
import com.ens.repo.news.NewsItemSocialShareRepository;
import com.ens.repo.news.UserCommentRepository;
import com.ens.repo.news.UserLikeRepository;
import com.ens.repo.news.UserUnLikeRepository;
import com.ens.service.ValidationService;
import com.ens.service.fcm.NotificationService;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Value("${app.default.scroll.text}")
    private String defaultScrollText;

    @Value("${app.fcm.news.notification.topic.name:news}")
    private String fcmNewsTopicName;

    @Value("${app.notification.icon.url}")
    private String appIconUrl;

    @Value("${app.name}")
    private String appName;

    @Autowired
    private NotificationService notificationService;

    @Override
    public NewsItem save(NewsItem newsItem) {
        return newsItemRepository.save(newsItem);
    }

    @Override
    public void delete(Long id) {
        newsItemRepository.deleteById(id);
    }

    @Override
    public Optional<NewsItem> findOne(Long id) {
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

    @Transactional
    @Override
    public NewsItem createNews(Long userId, NewsItemRequest newsRequest) {

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

        if (user.getUserType() != null && UserType.ADMIN.equals(user.getUserType()) ) {
            newsItem.setVisible(true);
        }else {
            newsItem.setVisible(false);
        }

        NewsItemLocation newsItemLocation = new NewsItemLocation();
        newsItemLocation.setArea(area);
        newsItemLocation.setDistrict(district);
        newsItemLocation.setState(state);
        newsItemLocation.setCountry(country);
        newsItemLocation.setInternational(newsRequest.isInternational());
        newsItemLocation.setNewsItem(newsItem);
        newsItemLocation.setUser(user);

        newsItem.setLocationInfo(newsItemLocation);

        NewsItem savedNewsItem = newsItemRepository.save(newsItem);

        try {
            // Sending Push Notification
            sendFCMNotification(savedNewsItem);
        }catch (Exception ex){
            log.error("{}",ex);
        }

        return savedNewsItem;
    }

    @Transactional
    @Override
    public NewsItem createVideo(Long userId, VideoRequest videoRequest) {

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

        if (user.getUserType() != null && UserType.ADMIN.equals(user.getUserType()) ) {
            newsItem.setVisible(true);
        }else {
            newsItem.setVisible(false);
        }

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

        NewsItem savedNewsItem = newsItemRepository.save(newsItem);

        try {
            // Sending Push Notification
            sendFCMNotification(savedNewsItem);
        }catch (Exception ex){
            log.error("{}",ex);
        }

        return savedNewsItem;
    }

    @Transactional
    @Override
    public void postComment(Long userId, Long newsItemId, String comment) {

        User user = validationService.validateUser(userId);
        NewsItem newsItem = validationService.validateNewsItem(newsItemId);

        UserComment userComment = new UserComment();
        userComment.setComment(comment);
        userComment.setNewsItem(newsItem);
        userComment.setUser(user);

        userCommentRepository.save(userComment);
    }

    @Transactional
    @Override
    public void postNewsItemAction(Long userId, Long newsItemId, ActionType actionType) {

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

    }

    @Override
    public NewsItemActionResponse getNewsItemAction(Long newsItemId) {
        return newsItemRepository.getNewsItemActionResponse(newsItemId).orElseGet(NewsItemActionResponse::new);
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

    @Transactional(readOnly = true)
    @Override
    public PagedResponse<NewsItemResponse> getNewsItems(Long userId, Set<ContentType> contentTypes, Long newsItemId, Boolean visible, int page, int size) {

        validationService.validatePageNumberAndSize(page,size);

        validationService.validateUser(userId);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "created_at");

        Set<String> names = new HashSet<>();
        contentTypes.stream().forEach(contentType -> names.add(contentType.name()));

        Page<NewsItemResponse> newsItems = newsItemRepository.getAllNewsItems(names,newsItemId,visible,pageable);

        return new PagedResponse<>(newsItems.getContent(), newsItems.getNumber(),
                newsItems.getSize(), newsItems.getTotalElements(), newsItems.getTotalPages(), newsItems.isLast());
    }

    @Override
    public ScrollResponse getNewsScrollText(Long userId, int page, int size) {

        validationService.validatePageNumberAndSize(page,size);

        validationService.validateUser(userId);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "created_at");

        Page<NewsItemResponse> newsItems = newsItemRepository.getAllNewsItems(Sets.newHashSet(ContentType.SCROLL.name()),0,true,pageable);

        if (newsItems == null || newsItems.isEmpty()) {
            return new ScrollResponse("");
        }

        StringBuilder scrollBuilder = new StringBuilder();

        newsItems.get().forEach(newsItem -> scrollBuilder.append(newsItem.getHeadLine()).append(" || "));

        return new ScrollResponse(scrollBuilder.toString());
    }

    @Override
    public NewsItemResponse getNewsItemById(Long userId, Long newsItemId) {
        return newsItemRepository.getNewsItemById(newsItemId).orElseThrow(() -> new ResourceNotFoundException("News","id",newsItemId));
    }

    @Transactional
    @Override
    public void approveNewsItem(Long userId, Long newsItemId) {

        User user = validationService.validateUser(userId);

        if (user == null || user.getUserType()==null || UserType.ADMIN != user.getUserType()){
            throw  new BadRequestException("User UnAuthorized to Approve News");
        }

        NewsItem newsItem = validationService.validateNewsItem(newsItemId);

        newsItem.setVisible(true);

        newsItemRepository.save(newsItem);
    }

    private void sendFCMNotification(NewsItem newsItem){

        PushNotificationRequest notificationRequest = new PushNotificationRequest();
        notificationRequest.setTopic(fcmNewsTopicName);
        notificationRequest.setTitle(newsItem.getHeadLine());
        notificationRequest.setMessage(newsItem.getHeadLine());

        if (StringUtils.isNotEmpty(newsItem.getImageUrl())) {
            notificationRequest.setImageUrl(newsItem.getImageUrl());
        }else if (newsItem.getVideo() != null && StringUtils.isNotEmpty(newsItem.getVideo().getThumbnailImageUrl())){
            notificationRequest.setImageUrl(newsItem.getVideo().getThumbnailImageUrl());
        }

        Map<String,String> data = new HashMap<>();
        data.put("title",newsItem.getHeadLine());
        data.put("content",newsItem.getHeadLine());
        data.put("newsItemId",String.valueOf(newsItem.getId()));
        if (StringUtils.isNotEmpty(newsItem.getImageUrl())) {
            data.put("imageUrl", newsItem.getImageUrl());
        }else if (StringUtils.isNotEmpty(newsItem.getVideo().getThumbnailImageUrl())){
            data.put("imageUrl", newsItem.getVideo().getThumbnailImageUrl());
        }
        data.put("userId",String.valueOf(newsItem.getUser().getId()));
        data.put("contentType",newsItem.getContentType().name());

        notificationRequest.setData(data);

        notificationService.sendNotification(notificationRequest);

    }

}
