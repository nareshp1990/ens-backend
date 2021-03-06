package com.ens.service.user;

import com.ens.domain.entity.user.User;
import com.ens.domain.entity.user.UserProfile;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.user.UserRequest;
import com.ens.domain.payload.user.UserResponse;
import com.ens.exception.AuthException;
import com.ens.exception.BadRequestException;
import com.ens.repo.user.UserRepository;
import com.ens.service.ValidationService;
import com.google.common.collect.Lists;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Value("${app.fcm.news.notification.topic.name:news}")
    private String fcmNewsTopicName;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pgbl) {
        return userRepository.findAll(pgbl);
    }

    @Transactional
    @Override
    public User createUser(UserRequest userRequest) {

        userRepository.findByMobileNumber(userRequest.getMobileNumber()).ifPresent(user -> { throw new BadRequestException(); });

        User userEntity = getUserEntity(userRequest);

        return save(userEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public PagedResponse<User> getAllUsers(int page, int size) {

        validationService.validatePageNumberAndSize(page, size);

        // Retrieve Polls
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<User> users = findAll(pageable);

        return new PagedResponse<>(users.getContent(), users.getNumber(),
                users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast());
    }

    @Override
    public User getUserById(Long userId) {
        return validationService.validateUser(userId);
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        validationService.validateUser(userId);
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public User updateUser(Long userId, UserRequest updateRequest) {

        User user = validationService.validateUser(userId);

        return user;
    }

    @Transactional
    @Override
    public User updateUserFCMKey(Long userId, String fcmKey) {

        User user = validationService.validateUser(userId);

        user.setFcmRegistrationKey(fcmKey);

        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(Lists.newArrayList(fcmKey),fcmNewsTopicName);
            log.info("### Topic Subscription Response : {}",response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            log.error("{}",e);
        }

        return save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User login(String mobileNumber, String password) {

        return userRepository.findByMobileNumberAndPassword(mobileNumber, password).orElseThrow(() -> new AuthException("UserId or Password incorrect"));

    }


    private User getUserEntity(UserRequest userRequest) {

        User user = new User();

        user.setEmail(userRequest.getEmail());
        user.setUserName(userRequest.getUserName());
        user.setMobileNumber(userRequest.getMobileNumber());
        user.setFcmRegistrationKey(userRequest.getFcmRegistrationKey());
        user.setPassword(userRequest.getPassword());
        user.setProfileImageUrl(userRequest.getProfileImageUrl());
        user.setUserType(userRequest.getUserType());

        UserProfile userProfile = new UserProfile();
        userProfile.setGender(userRequest.getGender());
        userProfile.setDateOfBirth(userRequest.getDateOfBirth());

        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        return user;
    }
}
